package com.example.spring_core_task.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrainerTest {
    @Test
    public void testTrainerConstructor() {
        Trainer trainer = new Trainer();
        assertNotNull(trainer);
    }

    @Test
    public void testGettersAndSetters() {
        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setFirstName("John");
        trainer.setLastName("Doe");
        trainer.setUserName("johndoe");
        trainer.setPassword("password");
        trainer.setActive(true);
        trainer.setSpecialization(TrainingType.valueOf("FLEXIBILITY"));

        assertEquals(1L, trainer.getId());
        assertEquals("John", trainer.getFirstName());
        assertEquals("Doe", trainer.getLastName());
        assertEquals("johndoe", trainer.getUserName());
        assertEquals("password", trainer.getPassword());
        assertTrue(trainer.isActive());
        assertEquals("FLEXIBILITY", trainer.getSpecialization().toString());
    }
}
