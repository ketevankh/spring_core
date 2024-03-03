package com.example.spring_core_task.daoImpl;

import com.example.spring_core_task.model.Training;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TrainingDaoImplTest {
    private TrainingDaoImpl trainingDao;

    @BeforeEach
    public void setUp() {
        Map<Long, Training> trainingStorage = new HashMap<>();
        trainingDao = new TrainingDaoImpl(trainingStorage);
    }

    @Test
    public void testCreateTraining() {
        Training training = new Training();
        training.setId(1L);
        training.setTrainingName("CARDIO");
        Optional<Long> id = trainingDao.create(training);
        Assertions.assertTrue(id.isPresent());

        Optional<Long> emptyTraining = trainingDao.create(training);
        Assertions.assertFalse(emptyTraining.isPresent());
    }

    @Test
    public void testGetTrainingById() {
        Training training = new Training();
        training.setId(1L);
        training.setTrainingName("CARDIO");
        trainingDao.create(training);
        Optional<Training> retrievedTraining = trainingDao.getById(1L);
        Assertions.assertTrue(retrievedTraining.isPresent());
        Assertions.assertEquals(training, retrievedTraining.get());

        Optional<Training> emptyTraining = trainingDao.getById(2L);
        Assertions.assertFalse(emptyTraining.isPresent());
    }

    @Test
    public void testGetAllTraining() {
        Assertions.assertFalse(trainingDao.getAllTraining().isPresent());
        Training training1 = new Training();
        training1.setId(1L);
        training1.setTrainingName("BALANCE");
        Training training2 = new Training();
        training2.setId(2L);
        training2.setTrainingName("STRENGTH");
        trainingDao.create(training1);
        trainingDao.create(training2);
        Optional<List<Training>> allTraining = trainingDao.getAllTraining();
        Assertions.assertTrue(allTraining.isPresent());
        List<Training> trainingList = allTraining.get();
        Assertions.assertEquals(2, trainingList.size());
        Assertions.assertTrue(trainingList.contains(training1));
        Assertions.assertTrue(trainingList.contains(training2));
    }

}
