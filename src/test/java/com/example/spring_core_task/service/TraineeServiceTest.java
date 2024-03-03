package com.example.spring_core_task.service;

import com.example.spring_core_task.dao.TraineeDao;
import com.example.spring_core_task.model.Trainee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TraineeServiceTest {

    private TraineeService traineeService;

    @Mock
    private TraineeDao traineeDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        traineeService = new TraineeService(traineeDao);
    }

    @Test
    void testCreateTrainee() {
        Trainee trainee = new Trainee(1L, "John", "Doe");

        when(traineeDao.create(trainee)).thenReturn(Optional.of(1L));

        Optional<Long> result = traineeService.createTrainee(trainee);

        assertEquals(Optional.of(1L), result);
        verify(traineeDao, times(1)).create(trainee);
    }

    @Test
    void testUpdateTrainee() {
        Trainee trainee = new Trainee(1L, "John", "Doe");
        trainee.setId(1L);

        when(traineeDao.update(trainee)).thenReturn(Optional.of(1L));

        Optional<Long> result = traineeService.updateTrainee(trainee);

        assertEquals(Optional.of(1L), result);
        verify(traineeDao, times(1)).update(trainee);
    }

    @Test
    void testGetTrainee() {
        Long traineeId = 1L;
        Trainee trainee = new Trainee(1L, "John", "Doe");
        trainee.setId(traineeId);

        when(traineeDao.get(traineeId)).thenReturn(Optional.of(trainee));

        Optional<Trainee> result = traineeService.getTrainee(traineeId);

        assertEquals(Optional.of(trainee), result);
        verify(traineeDao, times(1)).get(traineeId);
    }

    @Test
    void testDeleteTrainee() {
        Long traineeId = 1L;

        when(traineeDao.delete(traineeId)).thenReturn(Optional.of(traineeId));

        Optional<Long> result = traineeService.deleteTrainee(traineeId);

        assertEquals(Optional.of(traineeId), result);
        verify(traineeDao, times(1)).delete(traineeId);
    }
}
