package producers

import org.apache.kafka.clients.producer.{ProducerRecord,KafkaProducer}

class RandomProducer {
  def produce()={
    val topic="test_random"
    val props=new java.util.Properties()
    props.put("bootstrap.servers","localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer")
    val producer= new KafkaProducer[String,Integer](props)
    val rand=scala.util.Random
    producer.initTransactions()
    for(i <- 1 to 10){
      producer.beginTransaction()
      var record=new ProducerRecord[String,Integer](topic,i.toString(),rand.nextInt)
      producer.send(record)
    }
    producer.abortTransaction()
    producer.close
  }
}