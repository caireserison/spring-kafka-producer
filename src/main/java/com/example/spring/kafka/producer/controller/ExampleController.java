package com.example.spring.kafka.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.kafka.producer.service.ExampleService;

@RestController
public class ExampleController {

	@Autowired
	ExampleService exampleService;
	
	@GetMapping(path = "/test-kafka/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String test(@PathVariable(value = "name") String name) {
		return exampleService.testKafka(name);
	}
}
