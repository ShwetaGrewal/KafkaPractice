1. Each consumer(from consumer group) has 1:N relationship with partitions and hence consumer group should have consumers<=partitions in the subscribed topic.

2. Rebalance: Moving partition ownership from one consumer to another is called a rebalance and causes unavaialability but also scalability and efficiency. 
   Partitions are assigned using implementation of PartitionAssignor to decide which partitions should be handled by which consumer.Kafka has two built-in
   partition assignment policies: Range and RoundRobin. Partition assignment can be customized. Also, a callback can be created to make sure some actions happen before and after 
   reassignment(See ConsumerRebalanceListener in class CustomUserConsumer)

3.The first time you call poll() with a new consumer, it is responsible for finding the GroupCoordinator, joining the consumer group, and receiving a partition
  assignment. If a rebalance is triggered, it will be handled inside the poll loop as well. The heartbeats that keep consumers alive are sent from 
  within the poll loop. As poll loop has a limit in terms of time, try to make sure that whatever processing goes between iterations is fast and efficient.

4. Handling commits: 
	4.1.1 enable.auto.commit=true (enable.auto.commit.interval.ms)
	4.1.2 commitSync() : Commits latest offset returned by poll(), if commit fails it gives an exception. Do it insidepoll loop
	4.1.3 commitAsync(): can have a callback which works according to result of commit, but doesn't wait for result of cimmit. 
	4.1.4 Combine sync and async:  if we know that this is the last commit before we close the consumer, or before a rebalance, 
	      we want to make extra sure that the commit succeeds. Socombine commitAsync() with commitSync() just before shutdown

5. Read from specific part:
	5.1  seekToBeginning(TopicPartition tp)
	5.2  seekToEnd(TopicPartition tp).
	5.3  seek(TopicPartition tp)
	
