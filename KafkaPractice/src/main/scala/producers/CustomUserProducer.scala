package producers

import org.apache.kafka.clients.producer.{ProducerRecord,KafkaProducer,RecordMetadata,Callback}
import java.util.Properties
import customtypes.User

import scala.concurrent.Promise

class CustomUserProducer {
  def produce():Unit={
    val topic="test_custom"
    val props=new Properties()
    props.put("bootstrap.servers","localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "serdes.CustomUserSerializer")
    val producer= new KafkaProducer[Long,User](props)
    var user=new User(1,"userOne","4567578698","none")
    val record=new ProducerRecord[Long,User](topic,user.getID(),user)
    producer.send(record)
    producer.close
  }
  def sendSync(producer:KafkaProducer[Long,User],record:ProducerRecord[Long,User]):Unit={
    try {
      producer.send(record).get() 
    } catch {
      case exception: Exception => exception.printStackTrace() // TODO: handle error
    }
  }
  
  def sendAsync(producer:KafkaProducer[Long,User],record:ProducerRecord[Long,User]):Unit={
    //val promise = Promise[(RecordMetadata, Exception)]()
    producer.send(record, new Callback {
      override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
        /*promise.success((metadata, exception))
        promise.failure(exception)*/
        if(metadata!=null){
          print(metadata.toString)
        }else{
          exception.printStackTrace
        }
      }
    })
  }
}