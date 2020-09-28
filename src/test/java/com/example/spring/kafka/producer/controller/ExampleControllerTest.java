package com.example.spring.kafka.producer.controller;

import com.example.spring.kafka.producer.service.ExampleService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleControllerTest {
    private static final String SUCCESS_MESSAGE = "Name Erison entered successfully!";

    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    ExampleService exampleService;

    @Test
    public void testName() {
        Mockito
                .when(exampleService.testKafka(Mockito.anyString()))
                .thenReturn(SUCCESS_MESSAGE);

        String response = restTemplate.getForObject("/test-kafka/name/Erison", String.class);
        Assertions.assertThat(response).isEqualTo(SUCCESS_MESSAGE);
    }
}
