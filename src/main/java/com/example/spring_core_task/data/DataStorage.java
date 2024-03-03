package com.example.spring_core_task.data;

import com.example.spring_core_task.model.Trainee;
import com.example.spring_core_task.model.Trainer;
import com.example.spring_core_task.model.Training;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Getter @Setter
public class DataStorage {
    private Map<Long, Trainee> traineeStorage;
    private Map<Long, Trainer> trainerStorage;
    private Map<Long, Training> trainingStorage;
    private ObjectMapper objectMapper;

    @Autowired
    public DataStorage(Map<Long, Trainee> traineeStorage,
                       Map<Long, Trainer> trainerStorage,
                       Map<Long, Training> trainingStorage, ObjectMapper objectMapper) {
        this.traineeStorage = traineeStorage;
        this.trainerStorage = trainerStorage;
        this.trainingStorage = trainingStorage;
        this.objectMapper = objectMapper;
    }
    public DataStorage(Map<Long, Trainee> traineeStorage,
                       Map<Long, Trainer> trainerStorage,
                       Map<Long, Training> trainingStorage) {
        this.traineeStorage = traineeStorage;
        this.trainerStorage = trainerStorage;
        this.trainingStorage = trainingStorage;
        this.objectMapper = new ObjectMapper();
    }
}
