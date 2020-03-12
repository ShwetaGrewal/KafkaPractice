package customtypes

class User(id:Long,name:String,phNumber:String,subscription:String) extends Serializable{
  def getID():Long={
    return id
  }
  
  def getName():String={
    return name
  }
  
  def getPhNumber():String={
    return phNumber
  }
  
  def getSubscription():String={
    return subscription
  }
  
  override def toString():String={
    return id.toString()+", "+name+", "+phNumber+", "+subscription
  }
}