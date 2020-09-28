package com.example.spring.kafka.producer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleServiceTest {

    @Mock
    KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    ExampleService exampleService;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(exampleService, "kafkaTopicContract", "TOPIC_TEST");
    }

    @Test
    public void testKafka() {
        Mockito
                .when(kafkaTemplate.send(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(null);

        exampleService.testKafka("Erison");
        Mockito.verify(kafkaTemplate, Mockito.times(1)).send(Mockito.anyString(), Mockito.anyString());
    }
}
