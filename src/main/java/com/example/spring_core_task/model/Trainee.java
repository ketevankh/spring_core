package com.example.spring_core_task.model;

import lombok.Data;

import java.util.Date;

@Data
public class Trainee extends User {
    private Long id;
    private Date dateOfBirth;
    private String address;
    public Trainee(){}
    public Trainee(Long id, String firstName, String lastName) {
        super(firstName, lastName);
        this.id = id;
    }
}