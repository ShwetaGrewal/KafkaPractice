package Main

import producers.{FirstProducer,RandomProducer,CustomUserProducer}
import consumers.{FirstConsumer,RandomConsumer,CustomUserConsumer}

object run {
  def main(args: Array[String]): Unit = {
    if(args(0).equals("producer")){
      if(args(1).equals("first")){
        val producer=new FirstProducer()
        producer.produce()
      }else if(args(1).equals("random")){
        val producer=new RandomProducer()
        producer.produce()
      }else if(args(1).equals("user")){
        val producer=new CustomUserProducer()
        producer.produce()
      }
    }else if(args(0).equals("consumer")){
       if(args(1).equals("first")){
        val consumer=new FirstConsumer("sync")
        consumer.consume()
      }else if(args(1).equals("random")){
        val consumer=new RandomConsumer()
        consumer.consume()
      }else if(args(1).equals("user")){
        val consumer=new CustomUserConsumer()
        consumer.consume()
      } 
    }
  }
}