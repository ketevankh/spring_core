package com.example.spring_core_task.service;


import com.example.spring_core_task.dao.TraineeDao;
import com.example.spring_core_task.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TraineeService {
    private final TraineeDao traineeDao;

    @Autowired
    public TraineeService(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }

    public Optional<Long> createTrainee(Trainee trainee) {
        return traineeDao.create(trainee);
    }

    public Optional<Long> updateTrainee(Trainee trainee) {
        return traineeDao.update(trainee);
    }

    public Optional<Trainee> getTrainee(Long id) {
        return traineeDao.get(id);
    }

    public Optional<Long> deleteTrainee(Long id) {
        return traineeDao.delete(id);
    }
}
