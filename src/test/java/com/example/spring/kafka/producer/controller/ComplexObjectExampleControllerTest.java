package com.example.spring.kafka.producer.controller;

import com.example.spring.kafka.producer.entity.ObjectExample;
import com.example.spring.kafka.producer.service.ComplexObjectExampleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComplexObjectExampleControllerTest {
    private static final String SUCCESS_MESSAGE = "Sent with success";

    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    ComplexObjectExampleService complexObjectExampleService;

    ObjectExample objectExample;

    @Before
    public void setup() {
        objectExample = new ObjectExample();
        objectExample.setId(1L);
        objectExample.setName("Erison");
        objectExample.setDateBirth(LocalDate.now().minusYears(30));
    }

    @Test
    public void testSendBirth() throws JsonProcessingException {
        Mockito
                .when(complexObjectExampleService.send(Mockito.any(ObjectExample.class)))
                .thenReturn(SUCCESS_MESSAGE);

        String response = restTemplate.postForObject("/people", objectExample, String.class);
        Assertions.assertThat(response).isEqualTo(SUCCESS_MESSAGE);
    }

    @Test
    public void testSendBirthException() throws JsonProcessingException {
        Mockito
                .doThrow(new JsonProcessingException("TEST"){})
                .when(complexObjectExampleService)
                .send(Mockito.any(ObjectExample.class));

        String response = restTemplate.postForObject("/people", objectExample, String.class);
        Assertions.assertThat(response).isEqualTo("TEST");
    }
}
