package com.example.spring_core_task.dao.impl;

import com.example.spring_core_task.dao.TraineeDao;
import com.example.spring_core_task.model.Trainee;
import com.example.spring_core_task.model.Trainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class TraineeDaoImpl extends UserDaoImpl implements TraineeDao {
    @Autowired
    public TraineeDaoImpl(Map<Long, Trainee> traineeStorage, Map<Long, Trainer> trainerStorage) {
        super(traineeStorage, trainerStorage);
    }
    @Override
    public Optional<Long> create(Trainee trainee) {
        if(traineeStorage.containsKey(trainee.getId())) {
            log.info("Trainee already exists");
            return Optional.of(traineeStorage.get(trainee.getId()).getId());
        }
        trainee.setPassword(generatePassword());
        trainee.setUserName(generateUserName(trainee.getFirstName(), trainee.getLastName()));
        traineeStorage.put(trainee.getId(), trainee);
        return Optional.of(trainee.getId());
    }
    @Override
    public Optional<Long> update(Trainee trainee) {
        if(!traineeStorage.containsKey(trainee.getId())) {
            log.info("Trainee does not exist");
            return Optional.empty();
        }
        Trainee tmpTrainee = traineeStorage.get(trainee.getId());
        if(!(tmpTrainee.getUserName().equals(trainee.getUserName()))) {
            log.info("Trainee username cannot be updated");
            return Optional.empty();
        }
        if(trainee.getPassword().length()!= PASSWORD_LENGTH) {
            log.info("Trainee password length is not valid");
            return Optional.empty();
        }
        traineeStorage.put(trainee.getId(), trainee);
        return Optional.of(trainee.getId());
    }

    @Override
    public Optional<Long> delete(Long id) {
        if(!traineeStorage.containsKey(id)) {
            log.info("Trainee does not exist");
            return Optional.empty();
        }
        traineeStorage.remove(id);
        return Optional.of(id);
    }
    @Override
    public Optional<Trainee> get(Long id) {
        if(!traineeStorage.containsKey(id)) {
            log.info("Trainee does not exist");
            return Optional.empty();
        }
        return Optional.of(traineeStorage.get(id));
    }
}
