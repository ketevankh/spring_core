package com.example.spring_core_task.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserConstructor() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void testGettersAndSetters() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUserName("johndoe");
        user.setPassword("password");
        user.setActive(true);

        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe", user.getUserName());
        assertEquals("password", user.getPassword());
        assertTrue(user.isActive());
    }
}
