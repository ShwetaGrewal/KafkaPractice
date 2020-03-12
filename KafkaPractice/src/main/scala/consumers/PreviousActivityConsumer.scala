package consumers

import org.apache.kafka.clients.consumer.KafkaConsumer
import customtypes.Activity
import org.apache.kafka.common.TopicPartition
import scala.collection.JavaConverters._

/*
 * consumer for one/specific partition/s 
 * consumes the activities that were done before today (ActivityPartitioner example)*/
class PreviousActivityConsumer {
  def consume()={
    val topic="test_customactivity"
    val props=new java.util.Properties()
    //not adding group id, since it has to read only one partition
    props.put("bootstrap.servers","localhost:9092")
    props.put("enable.auto.commit", "true")
    props.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
    props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
    props.put("schema.registry.url", "localhost:8081")
    
    val consumer= new KafkaConsumer[Long,Activity](props)
    val partitions=consumer.partitionsFor(topic)
    if(partitions!=null){
      consumer.assign(java.util.Collections.singletonList(new TopicPartition(topic,partitions.size()-1)))
      while(true){
        val consumerRecords=consumer.poll(60)
        for(record<-consumerRecords.asScala){println(record.key().toString()+", "+record.key().toString())}
      }      
    }
    println("no partitions to assign")
    consumer.close
  }

}