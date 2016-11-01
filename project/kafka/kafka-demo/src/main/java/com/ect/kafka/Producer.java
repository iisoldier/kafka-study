package com.ect.kafka;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.google.common.io.Resources;

public class Producer {

	public static void main(String[] args) throws IOException {
        // set up the producer
        KafkaProducer<String, String> producer;
        try (InputStream props = Resources.getResource("producer.properties").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            producer = new KafkaProducer<>(properties);
        }
      
        try {
            for (int i = 0; i < 1000; i++) {
            	producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
            	System.out.println("key； "+i+" value: "+i);
            }
        } catch (Throwable throwable) {
        	System.out.println(throwable.getStackTrace());
        } finally {
            producer.close();
        }

    }
}
