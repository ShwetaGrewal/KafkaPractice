package customtypes

class Activity(timestamp:Long,userid:Long,activity:String,screen:String) {

  def getTimestamp():Long={
  	return timestamp;
  }
  
  def getUserid():Long= {
  	return userid;
  }
  
  def getActivity():String={
  	return activity;
  }
  
  def getScreen():String= {
  	return screen;
  }
  
  override def toString():String= {
  	return timestamp.toString()+","+userid.toString()+","+activity+","+screen;
  }
}

