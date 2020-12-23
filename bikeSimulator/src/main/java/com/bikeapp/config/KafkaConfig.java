package com.bikeapp.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.postgresql.core.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.bikeapp.bean.Bike;
import com.bikeapp.bean.BikeEvent;
import com.bikeapp.bean.BikeLog;

import org.springframework.kafka.support.serializer.JsonSerializer;



@Configuration
public class KafkaConfig {
	/*
	 * @Value("${kafka.boot.server}") private String kafkaServer;
	 */
	 @Bean
	    public ProducerFactory<String, Bike> producerFactory() {
	        Map<String, Object> config = new HashMap<>();

	        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
	        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

	        return new DefaultKafkaProducerFactory<>(config);
	    }
	 
	 @Bean
	    public ProducerFactory<String, BikeEvent> producerFactoryBikeEvent() {
	        Map<String, Object> config = new HashMap<>();

	        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
	        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

	        return new DefaultKafkaProducerFactory<>(config);
	    }
	 
	 @Bean
	    public ProducerFactory<String, BikeLog> producerFactoryBikeLog() {
	        Map<String, Object> config = new HashMap<>();

	        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
	        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

	        return new DefaultKafkaProducerFactory<>(config);
	    }

	    @Bean
	    public KafkaTemplate<String, Bike> kafkaTemplate() {
	        return new KafkaTemplate<>(producerFactory());
	    }
	    
	    @Bean
	    public KafkaTemplate<String, BikeEvent> kafkaTemplateBikeEvent() {
	        return new KafkaTemplate<>(producerFactoryBikeEvent());
	    }
	    
	    @Bean
	    public KafkaTemplate<String, BikeLog> kafkaTemplateBikeLog() {
	        return new KafkaTemplate<>(producerFactoryBikeLog());
	    }
}
