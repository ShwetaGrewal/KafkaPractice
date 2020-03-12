package serdes

import org.apache.kafka.common.serialization
import org.apache.kafka.common.serialization.Serializer
import org.apache.commons.lang3.SerializationUtils
import customtypes.User
import java.util.Map

class CustomUserSerializer extends Serializer[User]{
  def configure(configs:Map[String,_],isKey:Boolean):Unit={}
  
  def serialize(topic:String,user:User):Array[Byte]={
    return SerializationUtils.serialize(user)
  }
  
  def close()={}
}