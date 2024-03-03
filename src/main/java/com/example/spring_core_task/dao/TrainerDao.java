package com.example.spring_core_task.dao;

import com.example.spring_core_task.model.Trainer;

import java.util.Optional;

public interface TrainerDao {
    Optional<Long> create(Trainer trainer);
    Optional<Long> update(Trainer trainer);
    Optional<Trainer> get(Long id);
}

