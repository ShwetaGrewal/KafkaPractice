package consumers

import org.apache.kafka.clients.consumer.{KafkaConsumer,OffsetCommitCallback,OffsetAndMetadata,ConsumerRecord}
import org.apache.kafka.common.TopicPartition
import java.util.Collections
import scala.collection.JavaConverters._

class FirstConsumer(commitType:String) {
  def consume():Unit={
    val topic="test"
    val props=new java.util.Properties()
    props.put("bootstrap.servers","localhost:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("enable.auto.commit", "false")
    val consumer=new KafkaConsumer[String,String](props)
    consumer.subscribe(Collections.singletonList(topic))
    try{
      while(true){
        val consumerRecords=consumer.poll(60)
        try{
          for(record<-consumerRecords.asScala){
            println(s"$record.key(): $record.value")
          }
          commit(commitType,consumer) //because auto commit is disabled
        }catch{
          case exception:Exception=>exception.printStackTrace
        }
      }
    }finally{
      consumer.close()
    }
  }
  
  def commit(commitType: String,consumer:KafkaConsumer[String,String]):Unit={
    if(commitType.equals("sync")){
      consumer.commitSync()
    }else{
      consumer.commitAsync(new OffsetCommitCallback {
        override def onComplete(offsets:java.util.Map[TopicPartition,OffsetAndMetadata], exception:java.lang.Exception): Unit = {
          if(exception!=null){
                println("commit failed for offsets {"+offsets+"} with exception "+exception.getMessage)
          }
        }
      })
    }
    
  }
}