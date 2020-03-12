package consumers

import org.apache.kafka.clients.consumer.{KafkaConsumer,ConsumerRebalanceListener,OffsetAndMetadata}
import org.apache.kafka.common.TopicPartition
import java.util.Collection
import customtypes.User
import scala.collection.JavaConverters._

class CustomUserConsumer {
  def consume():Unit={
    val topic="test_custom"
    val props=new java.util.Properties()
    props.put("bootstrap.servers","localhost:9092")
    props.put("enable.auto.commit", "false")
    props.put("group.id","custom_consumer")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.LongDeserializer")
    props.put("value.deserializer", "serdes.CustomUserDeserializer")
    val consumer= new KafkaConsumer[String,User](props)
    var offsets=new java.util.HashMap[TopicPartition,OffsetAndMetadata]
    /*
     * ConsumerRebalanceListerner handles what happens when rebalance occurs:
     * 1. Can commit offsets
     * 2. Can flush cache*/
    consumer.subscribe(java.util.Collections.singletonList(topic), new ConsumerRebalanceListener{
      def onPartitionsAssigned(partitions:Collection[TopicPartition]):Unit={}
      def onPartitionsRevoked(partitions:Collection[TopicPartition]):Unit={
        println("committing offsets before rebalance")
        consumer.commitSync(offsets)
      }
    })
    
    try{
      while(true){
        val consumerRecords=consumer.poll(60)
        for(record<-consumerRecords.asScala){
          println(record.key().toString()+", "+record.value().toString())
          offsets.put(new TopicPartition(record.topic(),record.partition()), new OffsetAndMetadata(record.offset(),""))
          consumer.commitAsync()
        }
      }
    }catch{
      case exception:Exception=>exception.printStackTrace()
    }finally{
      consumer.close
    }
  }
}