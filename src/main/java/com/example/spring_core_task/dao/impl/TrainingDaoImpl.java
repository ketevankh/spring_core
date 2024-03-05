package com.example.spring_core_task.dao.impl;

import com.example.spring_core_task.dao.TrainingDao;
import com.example.spring_core_task.model.Training;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class TrainingDaoImpl implements TrainingDao {
    public final Map<Long, Training> trainingStorage;

    public TrainingDaoImpl(Map<Long, Training> trainingStorage) {
        this.trainingStorage = trainingStorage;
    }

    @Override
    public Optional<Long> create(Training training) {
        if(trainingStorage.containsKey(training.getId())) {
            log.info("Training already exists");
            return Optional.of(trainingStorage.get(training.getId()).getId());
        }
        trainingStorage.put(training.getId(), training);
        return Optional.of(training.getId());
    }

    @Override
    public Optional<Training> getById(Long id) {
        if(!trainingStorage.containsKey(id)) {
            log.info("Training does not exist");
            return Optional.empty();
        }
        return Optional.of(trainingStorage.get(id));
    }

    @Override
    public List<Training> getAllTraining() {
        if(trainingStorage.isEmpty()) {
            log.info("No training exists");
            return Collections.emptyList();
        }
        return List.copyOf(trainingStorage.values());
    }
}
