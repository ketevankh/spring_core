package com.example.spring_core_task.dao;
import com.example.spring_core_task.model.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingDao {
    Optional<Long> create(Training training);
    Optional<Training> getById(Long id);
    Optional<List<Training>> getAllTraining();
}

