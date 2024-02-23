package com.Airlines.AirlinesManagementSystem.Config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafka
public class KafkaConfig {
	 @Bean
	    public NewTopic yourTopicName() {
	        return TopicBuilder.name("FlightStimulator")
	                .partitions(1)
	                .replicas(1)
	                .build();
	    }
}
