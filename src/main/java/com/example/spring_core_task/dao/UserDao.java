package com.example.spring_core_task.dao;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserDao {
    Set<String> getAllUserNames();

    boolean isUniqUserName(String userName);

    String generatePassword();

    String generateUserName(String firstName, String lastName);
}
