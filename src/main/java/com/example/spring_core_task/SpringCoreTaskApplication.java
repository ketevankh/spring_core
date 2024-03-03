package com.example.spring_core_task;

import com.example.spring_core_task.data.DataStorage;
import com.example.spring_core_task.model.Trainee;
import com.example.spring_core_task.model.Trainer;
import com.example.spring_core_task.model.Training;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Map;

@SpringBootApplication
public class SpringCoreTaskApplication {

    public static void main( String[] args )
    {
        ApplicationContext context = SpringApplication.run(SpringCoreTaskApplication.class, args);
        DataStorage dataStorage = context.getBean(DataStorage.class);
        Map<Long, Trainee> traineeMap = dataStorage.getTraineeStorage();
        traineeMap.forEach((k, v) -> System.out.println(k + " " + v));

        Map<Long, Trainer> trainerMap = dataStorage.getTrainerStorage();
        trainerMap.forEach((k, v) -> System.out.println(k + " " + v));

        Map<Long, Training> trainingMap = dataStorage.getTrainingStorage();
        trainingMap.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
