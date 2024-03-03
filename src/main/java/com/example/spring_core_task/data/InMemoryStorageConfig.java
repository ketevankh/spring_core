package com.example.spring_core_task.data;

import com.example.spring_core_task.model.Trainee;
import com.example.spring_core_task.model.Trainer;
import com.example.spring_core_task.model.Training;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class InMemoryStorageConfig {
    @Bean
    public Map<Long, Trainee> traineeStorage() {
        return new HashMap<>();
    }
    @Bean
    public Map<Long, Trainer> trainerStorage() {
        return new HashMap<>();
    }
    @Bean
    public Map<Long, Training> trainingStorage() {
        return new HashMap<>();
    }
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new SimpleModule().addDeserializer(DataStorage.class, new DataStorageDeserialization()));
    }
}
