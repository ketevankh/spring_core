package com.example.spring_core_task.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

@Data
public class User {
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String userName;
    @JsonIgnore
    private String password;
    private boolean isActive;
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = true;
    }
    public User() {
    }
}
