package com.es.itmyhome;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 *按照分区规则是实现了kafka.producer.Partitioner接口的类
 */
public class SimplePartitioner implements Partitioner {

    public SimplePartitioner (VerifiableProperties props) {
    }

    public int partition(Object key, int numPartitions) {
        int partition = 0;
        String k = (String)key;
        partition = Math.abs(k.hashCode()) % numPartitions;
        return partition;
    }

}
