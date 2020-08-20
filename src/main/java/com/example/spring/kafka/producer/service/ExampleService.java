package com.example.spring.kafka.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExampleService {
	@Value("${kafka.bootstrap.topic-contract}")
	private String kafkaTopicContract;
	
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	public String testKafka(String name) {
		
		kafkaTemplate.send(kafkaTopicContract, name);
		
		return "Name " + name + " entered successfully!";
	}
}
