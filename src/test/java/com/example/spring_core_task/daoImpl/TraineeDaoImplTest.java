package com.example.spring_core_task.daoImpl;


import com.example.spring_core_task.dao.impl.TraineeDaoImpl;
import com.example.spring_core_task.model.Trainee;
import com.example.spring_core_task.model.Trainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TraineeDaoImplTest {
    private TraineeDaoImpl traineeDao;

    @BeforeEach
    public void setUp() {
        Map<Long, Trainee> traineeStorage = new HashMap<>();
        Map<Long, Trainer> trainerStorage = new HashMap<>();
        traineeDao = new TraineeDaoImpl(traineeStorage, trainerStorage);
    }

    @Test
    public void testCreateTrainee() {
        Assertions.assertTrue(traineeDao.traineeStorage.isEmpty());

        Trainee trainee = new Trainee();
        trainee.setId(1L);
        trainee.setFirstName("John");
        trainee.setLastName("Doe");
        Optional<Long> id = traineeDao.create(trainee);
        Assertions.assertTrue(id.isPresent());
        Assertions.assertEquals(1L, id.get());
        Assertions.assertEquals(1, traineeDao.traineeStorage.size());

        Optional<Long> existedTrainee = traineeDao.create(trainee);
        Assertions.assertTrue(existedTrainee.isPresent());
        Assertions.assertEquals(1L, existedTrainee.get());
        Assertions.assertEquals(1, traineeDao.traineeStorage.size());
    }

    @Test
    public void testUpdateTrainee() {
        Trainee trainee = new Trainee();
        trainee.setId(1L);
        trainee.setFirstName("John");
        trainee.setLastName("Doe");
        traineeDao.create(trainee);

        trainee.setFirstName("UpdatedFirstName");
        trainee.setLastName("UpdatedLastName");

        Optional<Long> id = traineeDao.update(trainee);
        Assertions.assertTrue(id.isPresent());
        assertEquals(1L, id.get());
        Assertions.assertEquals("UpdatedFirstName", traineeDao.get(1L).get().getFirstName());
        Assertions.assertEquals("UpdatedLastName", traineeDao.get(1L).get().getLastName());

        Optional<Long> emptyTrainee = traineeDao.update(new Trainee());
        Assertions.assertFalse(traineeDao.update(new Trainee()).isPresent());

        Trainee traineeUserNameUpdated = new Trainee();
        traineeUserNameUpdated.setId(1L);
        traineeUserNameUpdated.setUserName("not generated");
        Optional<Long> usernameUpdate = traineeDao.update(traineeUserNameUpdated);
        Assertions.assertFalse(usernameUpdate.isPresent());

        Trainee traineePasswordUpdated = new Trainee();
        traineePasswordUpdated.setId(1L);
        traineePasswordUpdated.setUserName(trainee.getUserName());
        traineePasswordUpdated.setPassword("not proper length password");
        Optional<Long> passwordUpdate = traineeDao.update(traineePasswordUpdated);
        Assertions.assertFalse(passwordUpdate.isPresent());
    }

    private void assertEquals(long l, Long aLong) {
    }

    @Test
    public void testGetTraineeById() {
        Trainee trainee = new Trainee();
        trainee.setId(1L);
        trainee.setFirstName("John");
        trainee.setLastName("Doe");
        traineeDao.create(trainee);
        Optional<Trainee> retrievedTrainee = traineeDao.get(1L);
        Assertions.assertTrue(retrievedTrainee.isPresent());
        Assertions.assertEquals(trainee, retrievedTrainee.get());
        Optional<Trainee> emptyTrainee = traineeDao.get(2L);
        Assertions.assertFalse(emptyTrainee.isPresent());
    }

    @Test
    public void testDeleteTrainee() {
        Trainee trainee = new Trainee();
        trainee.setId(1L);
        trainee.setFirstName("John");
        trainee.setLastName("Doe");

        traineeDao.create(trainee);
        Optional<Long> id = traineeDao.delete(1L);
        Assertions.assertTrue(id.isPresent());
        Assertions.assertEquals(1L, id.get());
        Optional<Long> emptyTrainee = traineeDao.delete(1L);
        Assertions.assertFalse(emptyTrainee.isPresent());
    }

}
