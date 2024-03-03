package com.example.spring_core_task.model;
import lombok.Data;

import java.time.Duration;
import java.util.Date;

@Data
public class Training {
    private Long id;
    private Long trainerId;
    private Long traineeId;
    private String trainingName;
    private TrainingType trainingType;
    private Date trainingDate;
    private Duration trainigDuration;
}
