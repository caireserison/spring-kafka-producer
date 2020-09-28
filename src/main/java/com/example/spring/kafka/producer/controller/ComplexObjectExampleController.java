package com.example.spring.kafka.producer.controller;

import com.example.spring.kafka.producer.entity.ObjectExample;
import com.example.spring.kafka.producer.service.ComplexObjectExampleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComplexObjectExampleController {

    @Autowired
    ComplexObjectExampleService complexObjectExampleService;

    @PostMapping(path = "/people", produces = MediaType.APPLICATION_JSON_VALUE)
    public String sendBirth(@RequestBody ObjectExample request) {
        String response;

        try {
            response = complexObjectExampleService.send(request);
        } catch (JsonProcessingException e) {
            response = e.getMessage();
        }
        return response;
    }
}
