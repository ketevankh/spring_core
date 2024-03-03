package com.example.spring_core_task.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TraineeTest {
    @Test
    public void testTrainingConstructor() {
        Training training = new Training();
        assertNotNull(training);
    }

    @Test
    public void testGettersAndSetters() {
        Training training = new Training();
        training.setId(1L);
        training.setTrainerId(10L);
        training.setTraineeId(20L);
        training.setTrainingName("Cardio Training");
        training.setTrainingType(TrainingType.CARDIO);
        training.setTrainingDate(new Date(2020, 1, 1));
        training.setTrainigDuration(Duration.ofMinutes(60));

        assertEquals(1L, training.getId());
        assertEquals(10L, training.getTrainerId());
        assertEquals(20L, training.getTraineeId());
        assertEquals("Cardio Training", training.getTrainingName());
        assertEquals(TrainingType.CARDIO, training.getTrainingType());
        assertNotNull(training.getTrainingDate());
        assertEquals(Duration.ofMinutes(60), training.getTrainigDuration());
    }
}

