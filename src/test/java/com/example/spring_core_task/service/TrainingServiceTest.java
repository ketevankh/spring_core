package com.example.spring_core_task.service;

import com.example.spring_core_task.dao.TrainingDao;
import com.example.spring_core_task.model.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TrainingServiceTest {

    private TrainingService trainingService;

    @Mock
    private TrainingDao trainingDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trainingService = new TrainingService(trainingDao);
    }

    @Test
    void testCreateTraining() {
        Training training = new Training();

        when(trainingDao.create(training)).thenReturn(Optional.of(1L));

        Optional<Long> result = trainingService.createTraining(training);

        assertEquals(Optional.of(1L), result);
        verify(trainingDao, times(1)).create(training);
    }

    @Test
    void testGetTraining() {
        Long trainingId = 1L;
        Training training = new Training();
        training.setId(trainingId);

        when(trainingDao.getById(trainingId)).thenReturn(Optional.of(training));

        Optional<Training> result = trainingService.getTraining(trainingId);

        assertEquals(Optional.of(training), result);
        verify(trainingDao, times(1)).getById(trainingId);
    }

    @Test
    void testGetAllTraining() {
        List<Training> trainingList = new ArrayList<>();
        trainingList.add(new Training());
        trainingList.add(new Training());

        when(trainingDao.getAllTraining()).thenReturn(List.copyOf(trainingList));

        List<Training> result = trainingService.getAllTraining();

        assertEquals(trainingList, result);
        verify(trainingDao, times(1)).getAllTraining();
    }
}
