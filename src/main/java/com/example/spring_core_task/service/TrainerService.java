package com.example.spring_core_task.service;

import com.example.spring_core_task.dao.TrainerDao;
import com.example.spring_core_task.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerService {
    private final TrainerDao trainerDao;

    @Autowired
    public TrainerService(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }
    public Optional<Long> createTrainer(Trainer trainer) {
        return trainerDao.create(trainer);
    }

    public Optional<Long> updateTrainer(Trainer trainer) {
        return trainerDao.update(trainer);
    }

    public Optional<Trainer> getTrainer(Long id) {
        return trainerDao.get(id);
    }
}
