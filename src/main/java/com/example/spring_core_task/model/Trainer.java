package com.example.spring_core_task.model;

import lombok.Data;

@Data
public class Trainer extends User {
    private Long id;
    private TrainingType specialization;
    public Trainer(Long id, String firstName, String lastName, TrainingType specialization) {
        super(firstName, lastName);
        this.id = id;
        this.specialization = specialization;
    }
    public Trainer() {
    }

    public void toStrig() {
        System.out.println("Trainer{" +
                "id=" + id +
                ", specialization=" + specialization +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", isActive='" + isActive() + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}');
    }
}
