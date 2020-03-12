package serdes

import org.apache.kafka.common.serialization
import org.apache.kafka.common.serialization.Deserializer
import org.apache.commons.lang3.SerializationUtils
import customtypes.User
import java.util.Map

class CustomUserDeserializer extends Deserializer[User] {
  def configure(configs:Map[String,_],isKey:Boolean):Unit={}
  
  def deserialize(topic:String,bytes:Array[Byte]):User={
    return SerializationUtils.deserialize(bytes).asInstanceOf[User]
  }
  
  def close()={}
}