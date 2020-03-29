package com.hongfei.qi.clientuserservice;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerDemo {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("group.id","test-group");
        properties.put("enable.auto.commit","true");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("test-group"));
        while(true){
            ConsumerRecords<String, String> consumerRecords = consumer.poll(100000);
            for(ConsumerRecord<String,String> consumerRecord : consumerRecords){
                System.out.println(consumerRecord.key()+"========="+consumerRecord.value());
            }
        }
    }
}
