package consumers

import org.apache.kafka.clients.consumer.{KafkaConsumer,OffsetCommitCallback,OffsetAndMetadata}
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.clients.consumer.ConsumerRecords
import scala.collection.JavaConverters._

class RandomConsumer {
  def consume()={
    val topic="test_random"
    val props=new java.util.Properties()
    props.put("bootstrap.servers","localhost:9092")
    props.put("enable.auto.commit", "false")
    props.put("group.id","random_consumer")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer")
    val consumer= new KafkaConsumer[String,Integer](props)
    consumer.subscribe(java.util.Collections.singletonList(topic))
    var offsets=new java.util.HashMap[TopicPartition,OffsetAndMetadata]
    try{
      while(true){
        val consumerRecords=consumer.poll(60)
        var count=0
        for(record<-consumerRecords.asScala){
          println(record.key().toString()+", "+record.value().toString())
          offsets.put(new TopicPartition(record.topic(),record.partition()), new OffsetAndMetadata(record.offset()+1,""))
          count+=1
          if(count!=0 && count%2==0){
            consumer.commitAsync(offsets, new OffsetCommitCallback {
              override def onComplete(offsets:java.util.Map[TopicPartition,OffsetAndMetadata], exception:java.lang.Exception): Unit = {
                if(exception!=null){
                      println("commit failed for offsets {"+offsets+"} with exception "+exception.getMessage)
                }
              }
            })
          }
        }  
      }
    }finally{
      try{
       consumer.commitSync() // a final commit before consumer closes, to make sure there's no loss of data
      }catch{
        case exception:Exception=>println("commit failed with exception "+exception.getMessage)
      }
      consumer.close()
    }
  }
}