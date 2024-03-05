package com.example.spring_core_task.daoImpl;

import com.example.spring_core_task.dao.UserDao;
import com.example.spring_core_task.dao.impl.UserDaoImpl;
import com.example.spring_core_task.model.Trainee;
import com.example.spring_core_task.model.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserDaoImplTest {

    @Mock
    private Trainee trainee;

    @Mock
    private Trainer trainer;

    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDao = new UserDaoImpl(new HashMap<>(), new HashMap<>());
    }

    @Test
    public void testGetAllUserNames_Empty() {
        assertEquals(Collections.emptySet(), userDao.getAllUserNames());
    }

    @Test
    public void testGetAllUserNames_WithUsers() {
        when(trainee.getUserName()).thenReturn("user1");
        when(trainer.getUserName()).thenReturn("user2");

        Map<Long, Trainee> traineeMap = new HashMap<>();
        traineeMap.put(1L, trainee);
        userDao = new UserDaoImpl(traineeMap, Collections.singletonMap(2L, trainer));

        Set<String> expectedUserNames = new HashSet<>();
        expectedUserNames.add("user1");
        expectedUserNames.add("user2");

        assertEquals(expectedUserNames, userDao.getAllUserNames());
    }

    @Test
    public void testIsUniqUserName_Empty() {
        assertTrue(userDao.isUniqUserName("test"));
    }

    @Test
    public void testIsUniqUserName_ExistingUserName() {
        when(trainee.getUserName()).thenReturn("test");
        Map<Long, Trainee> traineeMap = new HashMap<>();
        traineeMap.put(1L, trainee);
        userDao = new UserDaoImpl(traineeMap, Collections.emptyMap());

        assertFalse(userDao.isUniqUserName("test"));
    }

    @Test
    public void testGeneratePasswrord_NotNull() {
        assertNotNull(userDao.generatePassword());
    }

    @Test
    public void testGenerateUserName_UniqueName() {
        String userName = userDao.generateUserName("John", "Doe");
        assertTrue(userDao.isUniqUserName(userName));
    }

    @Test
    public void testGenerateUserName_NonUniqueName() {
        when(trainee.getUserName()).thenReturn("John.Doe");
        Map<Long, Trainee> traineeMap = new HashMap<>();
        traineeMap.put(1L, trainee);
        userDao = new UserDaoImpl(traineeMap, Collections.emptyMap());

        String userName = userDao.generateUserName("John", "Doe");
        assertTrue(userName.startsWith("John.Doe"));
        assertTrue(userName.length() > "John.Doe".length());
        assertEquals("John.Doe1", userName);
    }
}
