package com.hongfei.qi.clientuserservice;


import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaProducerDemo {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        Producer<String ,String> producer = new KafkaProducer<>(properties);
        for(int i=10; i< 20; i++){
            ProducerRecord<String, String> record =
                    new ProducerRecord<>("test", Integer.toString(i), Integer.toString(i));
            Future<RecordMetadata> test = producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(exception == null){

                        System.out.println("msg suc");
                    }else{
                        exception.printStackTrace();
                        System.out.println("msg fail");
                    }
                }
            });
            RecordMetadata recordMetadata = null;
            try {
                recordMetadata = test.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(recordMetadata);

        }
        producer.close();
    }

}
