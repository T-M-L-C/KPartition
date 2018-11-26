package com.es.itmyhome;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * 带分区规则的生产者
 */
public class PartitionerProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "127.0.0.102:9092");
        props.put("partitioner.class", "com.itmyhome.es.SimplePartitioner");
        Producer<String, String> producer = new Producer<String, String>(new ProducerConfig(props));
        String topic = "lc";
        for(int i=0; i<=20; i++) {
            String k = "key" + i;
            String v = k + "--value" + i;
            producer.send(new KeyedMessage<String, String>(topic,k,v));
        }
        producer.close();
    }
}
