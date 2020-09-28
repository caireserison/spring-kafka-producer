package com.example.spring.kafka.producer.service;

import com.example.spring.kafka.producer.entity.ObjectExample;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ComplexObjectExampleService {
    @Value("${kafka.bootstrap.topic-complex-contract}")
    private String kafkaTopicContract;

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public String send(ObjectExample request) throws JsonProcessingException {
        String objectJson;

        // Configuração necessária para tratar problema do LocalDate no momento da conversão para json
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());

        // Convertendo o objeto para Json (para evitar maiores problemas no consumer)
        objectJson = objectMapper.writeValueAsString(request);

        // Enviando json para a fila
        kafkaTemplate.send(kafkaTopicContract, objectJson);

        return "Sent with success";
    }
}
