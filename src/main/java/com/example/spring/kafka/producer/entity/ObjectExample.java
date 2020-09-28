package com.example.spring.kafka.producer.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ObjectExample {
    private Long id;
    private String name;
    private LocalDate dateBirth;
}
