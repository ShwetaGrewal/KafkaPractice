package miscellanous

import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.Cluster
import org.apache.kafka.common.errors.InvalidPartitionsException
import java.util.Map
import java.time.{ZonedDateTime,ZoneId}

/*
 * If the activity is old, put it in last partition
 * all the activity from today will be put in other partitions through a hash function*/

class ActivityPartitioner extends Partitioner{
  def configure(configs:Map[String,_]):Unit={}
  def partition(topic:String, key:Any, keyBytes:Array[Byte], value:Any, valueBytes:Array[Byte], cluster:Cluster):Int={
    val numPartitions=cluster.partitionsForTopic(topic).size()
    if(keyBytes==null || !key.isInstanceOf[Long]){
      throw new InvalidPartitionsException("Wrong Key Type: Required Long found"+key.getClass().toString())
    }
    var partition=numPartitions-1
    var zone = ZoneId.of( "Asia/Kolkata" );
    var today = ZonedDateTime.now( zone ).toLocalDate().atStartOfDay( zone ).toEpochSecond() ;
    if(key.asInstanceOf[Long]>=today){
      partition=(key.asInstanceOf[Long]%(numPartitions-1)).asInstanceOf[Int]
    }
    return partition
  }
  def close():Unit={}
}