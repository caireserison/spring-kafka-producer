package com.example.spring.kafka.producer.service;

import com.example.spring.kafka.producer.entity.ObjectExample;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComplexObjectExampleServiceTest {
    private static final String SUCCESS_MESSAGE = "Sent with success";

    @Mock
    KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    ComplexObjectExampleService complexObjectExampleService;

    ObjectExample objectExample;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(complexObjectExampleService, "kafkaTopicContract", "TOPIC_TEST");

        objectExample = new ObjectExample();
        objectExample.setId(1L);
        objectExample.setName("Erison");
        objectExample.setDateBirth(LocalDate.now().minusYears(30));
    }

    @Test
    public void testSend() throws JsonProcessingException {
        Mockito
                .when(kafkaTemplate.send(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(null);

        String response = complexObjectExampleService.send(objectExample);
        Assertions.assertThat(response).isEqualTo(SUCCESS_MESSAGE);
    }
}
