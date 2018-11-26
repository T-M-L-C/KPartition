

### kafka分区规则
Kafka中可以将Topic从物理上划分成一个或多个分区（Partition），每个分区在物理上对应一个文件夹，以”topicName_partitionIndex”的命名方式

命名，该文件夹下存储这个分区的所有消息(.log)和索引文件(.index)，这使得Kafka的吞吐率可以水平扩展。

生产者在生产数据的时候，可以为每条消息指定Key，这样消息被发送到broker时，会根据分区规则选择被存储到哪一个分区中，如果分区规则设置的合理，

那么所有的消息将会被均匀的分布到不同的分区中，这样就实现了负载均衡和水平扩展。另外，在消费者端，同一个消费组可以多线程并发的从多个分区中同时消费数据

kafka创建topic (该实例创建一个带四个分区的topic,建议：分区数最好是broker数量的整数倍)

./kafka-topics.sh --zookeeper localhost:2181 --create --topic lc --partitions 4  --replication-factor 1

###生产者
现在用一个生产者示例（PartitionerProducer），向Topic lc中发送消息。该生产者使用的分区规则，就是上面的SimplePartitioner。从0-10一共11条消息，每条消息的key为”key”+index，消息内容为”key”+index+”–value”+index。比如：key0–value0、key1–value1、、、key10–value10。

###消费者
先启动消费者，再运行生产者。