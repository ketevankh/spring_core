package com.example.spring_core_task.dao;

import com.example.spring_core_task.model.Trainee;

import java.util.Optional;

public interface TraineeDao {
    Optional<Long> create(Trainee trainee);
    Optional<Long> update(Trainee trainee);
    Optional<Long> delete(Long id);
    Optional<Trainee> get(Long id);
}