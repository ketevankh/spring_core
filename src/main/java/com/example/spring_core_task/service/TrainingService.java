package com.example.spring_core_task.service;

import com.example.spring_core_task.dao.TrainingDao;
import com.example.spring_core_task.model.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {
    private final TrainingDao trainingDao;

    @Autowired
    public TrainingService(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public Optional<Long> createTraining(Training training) {
        return trainingDao.create(training);
    }

    public Optional<Training> getTraining(Long id) {
        return trainingDao.getById(id);
    }

    public Optional<List<Training>> getAllTraining() {
        return trainingDao.getAllTraining();
    }

}