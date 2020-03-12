package producers

import org.apache.kafka.clients.producer.{ProducerRecord,KafkaProducer}
import customtypes.User

class AvroSerailizerUserProducer {
  def produce()={
    val topic="test_multi"
    val props=new java.util.Properties()
    props.put("bootstrap.servers","localhost:9092")
    props.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
    props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
    props.put("schema.registry.url", "localhost:8081")
    val producer= new KafkaProducer[Long,User](props)
    val user=new User(2,"userTwo","4567578688","none")
    val record=new ProducerRecord[Long,User](topic,user.getID(),user)
    producer.send(record)
    producer.close
  }
}