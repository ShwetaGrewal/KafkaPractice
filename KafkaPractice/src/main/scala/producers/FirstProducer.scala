package producers

import org.apache.kafka.clients.producer.{ProducerRecord,KafkaProducer}
//import java.io.Exception

class FirstProducer {
  def produce()={
    val topic="test"
    val props=new java.util.Properties()
    props.put("bootstrap.servers","localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    val producer= new KafkaProducer[String,String](props)
    val record=new ProducerRecord[String,String](topic,"Hello","World")
    producer.send(record) 
    producer.close
  }
  
}