package com.example.spring_core_task.dao.impl;

import com.example.spring_core_task.dao.TrainerDao;
import com.example.spring_core_task.model.Trainee;
import com.example.spring_core_task.model.Trainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class TrainerDaoImpl extends UserDaoImpl implements TrainerDao {
    @Autowired
    public TrainerDaoImpl(Map<Long, Trainee> traineeStorage, Map<Long, Trainer> trainerStorage) {
        super(traineeStorage, trainerStorage);
    }
    @Override
    public Optional<Long> create(Trainer trainer) {
        if(trainerStorage.containsKey(trainer.getId())) {
            log.info("Trainer already exists");
            return Optional.of(trainerStorage.get(trainer.getId()).getId());
        }
        trainer.setPassword(generatePassword());
        trainer.setUserName(generateUserName(trainer.getFirstName(), trainer.getLastName()));
        trainerStorage.put(trainer.getId(), trainer);
        return Optional.of(trainer.getId());
    }

    @Override
    public Optional<Long> update(Trainer trainer) {
        if(!trainerStorage.containsKey(trainer.getId())) {
            log.info("Trainer does not exist");
            return Optional.empty();
        }
        Trainer tmpTrainer = trainerStorage.get(trainer.getId());
        if(!tmpTrainer.getUserName().equals(trainer.getUserName())) {
            log.info("Trainer username cannot be updated");
            return Optional.empty();
        }
        if(trainer.getPassword().length()!= PASSWORD_LENGTH) {
            log.info("Trainer password length is not valid");
            return Optional.empty();
        }
        trainerStorage.put(trainer.getId(), trainer);
        return Optional.of(trainer.getId());
    }

    @Override
    public Optional<Trainer> get(Long id) {
        if(!trainerStorage.containsKey(id)) {
            log.info("Trainer does not exist");
            return Optional.empty();
        }
        return Optional.of(trainerStorage.get(id));    }
}
