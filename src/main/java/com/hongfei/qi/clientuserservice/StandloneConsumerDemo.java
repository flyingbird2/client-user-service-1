package com.hongfei.qi.clientuserservice;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
@Controller
public class StandloneConsumerDemo {

    @GetMapping("/standlone")
    @ResponseBody
    public String standlone(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("auto.offset.reset", "earliest");
        properties.put("enable.auto.commit", "false");
        properties.put("group.id","test-group");
        properties.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        List<TopicPartition> partitions = new ArrayList<>();
        List<PartitionInfo> partitionInfos = consumer.partitionsFor("test-group");
        if(partitionInfos != null && !partitionInfos.isEmpty()){
            for(PartitionInfo partitionInfo : partitionInfos){
                partitions.add(new TopicPartition(partitionInfo.topic(),partitionInfo.partition()));
            }
        }
        consumer.assign(partitions);
        try{
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
            for(ConsumerRecord record : records){
                System.out.println("=======>"+record.topic()+" p="+record.partition()+" off = "+record.offset()+" v="+record.value());
            }
            consumer.commitSync();

        }
        }finally {
            consumer.commitSync();
            consumer.close();
        }

    }

}
