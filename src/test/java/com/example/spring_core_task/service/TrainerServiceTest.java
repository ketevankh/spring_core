package com.example.spring_core_task.service;

import com.example.spring_core_task.dao.TrainerDao;
import com.example.spring_core_task.model.Trainer;
import com.example.spring_core_task.model.TrainingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TrainerServiceTest {

    private TrainerService trainerService;

    @Mock
    private TrainerDao trainerDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trainerService = new TrainerService(trainerDao);
    }

    @Test
    void testCreateTrainer() {
        Trainer trainer = new Trainer(1L, "John", "Doe", TrainingType.STRENGTH);

        when(trainerDao.create(trainer)).thenReturn(Optional.of(1L));

        Optional<Long> result = trainerService.createTrainer(trainer);

        assertEquals(Optional.of(1L), result);
        verify(trainerDao, times(1)).create(trainer);
    }

    @Test
    void testUpdateTrainer() {
        Trainer trainer = new Trainer(1L, "John", "Doe", TrainingType.STRENGTH);
        trainer.setId(1L);

        when(trainerDao.update(trainer)).thenReturn(Optional.of(1L));

        Optional<Long> result = trainerService.updateTrainer(trainer);

        assertEquals(Optional.of(1L), result);
        verify(trainerDao, times(1)).update(trainer);
    }

    @Test
    void testGetTrainer() {
        Long trainerId = 1L;
        Trainer trainer = new Trainer(1L, "John", "Doe", TrainingType.STRENGTH);
        trainer.setId(trainerId);

        when(trainerDao.get(trainerId)).thenReturn(Optional.of(trainer));

        Optional<Trainer> result = trainerService.getTrainer(trainerId);

        assertEquals(Optional.of(trainer), result);
        verify(trainerDao, times(1)).get(trainerId);
    }
}
