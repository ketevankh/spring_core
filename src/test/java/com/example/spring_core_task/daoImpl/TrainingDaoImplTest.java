package com.example.spring_core_task.daoImpl;

import com.example.spring_core_task.dao.impl.TrainingDaoImpl;
import com.example.spring_core_task.model.Training;
import com.example.spring_core_task.model.TrainingType;
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
        Assertions.assertTrue(trainingDao.trainingStorage.isEmpty());

        Training training = new Training();
        training.setId(1L);
        training.setTrainingName("training1");
        training.setTrainingType(TrainingType.CARDIO);
        Optional<Long> id = trainingDao.create(training);
        Assertions.assertTrue(id.isPresent());
        Assertions.assertEquals(1L, id.get());
        Assertions.assertEquals("training1", trainingDao.trainingStorage.get(1L).getTrainingName());
        Assertions.assertEquals(TrainingType.CARDIO, trainingDao.trainingStorage.get(1L).getTrainingType());
        Assertions.assertEquals(1, trainingDao.trainingStorage.size());


        Optional<Long> existedTraining = trainingDao.create(training);
        Assertions.assertTrue(existedTraining.isPresent());
        Assertions.assertEquals(1L, existedTraining.get());
        Assertions.assertEquals(TrainingType.CARDIO, trainingDao.trainingStorage.get(1L).getTrainingType());
        Assertions.assertEquals(1, trainingDao.trainingStorage.size());
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
        Assertions.assertTrue(trainingDao.getAllTraining().isEmpty());
        Training training1 = new Training();
        training1.setId(1L);
        training1.setTrainingName("BALANCE");
        Training training2 = new Training();
        training2.setId(2L);
        training2.setTrainingName("STRENGTH");
        trainingDao.create(training1);
        trainingDao.create(training2);
        List<Training> allTraining = trainingDao.getAllTraining();
        Assertions.assertFalse(allTraining.isEmpty());
        Assertions.assertEquals(2, allTraining.size());
        Assertions.assertTrue(allTraining.contains(training1));
        Assertions.assertTrue(allTraining.contains(training2));
    }
}
