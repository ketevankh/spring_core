package com.example.spring_core_task.daoImpl;


import com.example.spring_core_task.dao.UserDao;
import com.example.spring_core_task.model.Trainee;
import com.example.spring_core_task.model.Trainer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    protected final Map<Long, Trainee> traineeStorage;
    protected final Map<Long, Trainer> trainerStorage;
    protected static final int PASSWORD_LENGTH = 10;

    @Override
    public Optional<Set<String>> getAllUserNames() {
        Set<String> userNames = new HashSet<>();
        traineeStorage.forEach((k, v) -> userNames.add(v.getUserName()));
        trainerStorage.forEach((k, v) -> userNames.add(v.getUserName()));
        return userNames.isEmpty() ? Optional.empty() : Optional.of(userNames);
    }

    @Override
    public boolean isUniqUserName(String userName) {
        return !(getAllUserNames().isPresent()) || !getAllUserNames().get().contains(userName);
    }

    @Override
    public String generatePassword() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, PASSWORD_LENGTH);
    }

    @Override
    public String generateUserName(String firstName, String lastName) {
        String userName = firstName + "." + lastName;
        if (!isUniqUserName(userName)) {
            int i = 1;
            while (!isUniqUserName(userName + i)) {
                i++;
            }
            userName = userName + i;
        }
        return userName;
    }
}

