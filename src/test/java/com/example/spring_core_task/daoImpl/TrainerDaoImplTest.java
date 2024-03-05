package com.example.spring_core_task.daoImpl;

import com.example.spring_core_task.dao.impl.TrainerDaoImpl;
import com.example.spring_core_task.model.Trainee;
import com.example.spring_core_task.model.Trainer;
import com.example.spring_core_task.model.TrainingType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TrainerDaoImplTest {

    private TrainerDaoImpl trainerDao;

    private Trainer trainer;
    private Trainer updatedTrainer;
    private Trainer userNameUpdatedTrainer;
    private Trainer invalidPasswordTrainer;


    @BeforeEach
    public void setUp() {
        Map<Long, Trainee> traineeStorage = new HashMap<>();
        Map<Long, Trainer> trainerStorage = new HashMap<>();
        trainerDao = new TrainerDaoImpl(traineeStorage, trainerStorage);

        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        trainer = new Trainer(id, firstName, lastName, TrainingType.valueOf("STRENGTH"));
        updatedTrainer = new Trainer(id, firstName, lastName, TrainingType.valueOf("BALANCE"));
        userNameUpdatedTrainer = new Trainer(id, "Jane", lastName, TrainingType.valueOf("STRENGTH"));
        userNameUpdatedTrainer.setUserName("newUserName");
        invalidPasswordTrainer = new Trainer(id, "Jane", lastName, TrainingType.valueOf("STRENGTH"));
        invalidPasswordTrainer.setPassword("123");
    }

    @Test
    public void testCreate() {
        Assertions.assertTrue(trainerDao.trainerStorage.isEmpty());

        Optional<Long> id = trainerDao.create(trainer);
        Assertions.assertTrue(id.isPresent());
        Assertions.assertEquals(1L, id.get());
        Assertions.assertEquals(1, trainerDao.trainerStorage.size());

        Optional<Long> existedTrainer = trainerDao.create(updatedTrainer);
        Assertions.assertTrue(existedTrainer.isPresent());
        Assertions.assertEquals(1L, existedTrainer.get());
        Assertions.assertEquals(1, trainerDao.trainerStorage.size());
    }

    @Test
    public void testUpdate() {
        assertFalse(trainerDao.update(trainer).isPresent());
        trainerDao.create(trainer);
        updatedTrainer.setUserName(trainer.getUserName());
        updatedTrainer.setPassword(trainer.getPassword());
        Optional<Long> id = trainerDao.update(updatedTrainer);
        Assertions.assertTrue(id.isPresent());
        assertEquals(1L, id.get());
        Assertions.assertEquals(TrainingType.valueOf("BALANCE"), trainerDao.get(1L).get().getSpecialization());

        Optional<Long> userNameUpdated = trainerDao.update(userNameUpdatedTrainer);
        Assertions.assertFalse(userNameUpdated.isPresent());

        invalidPasswordTrainer.setUserName(trainer.getUserName());
        Optional<Long> invalidPassword = trainerDao.update(invalidPasswordTrainer);
        Assertions.assertFalse(invalidPassword.isPresent());
}

    @Test
    public void testGet() {
        trainerDao.create(trainer);
        Optional<Trainer> retrievedTrainer = trainerDao.get(1L);
        Assertions.assertTrue(retrievedTrainer.isPresent());
        Assertions.assertEquals(trainer, retrievedTrainer.get());
        Optional<Trainer> emptyTrainer = trainerDao.get(2L);
        Assertions.assertFalse(emptyTrainer.isPresent());
    }
}
