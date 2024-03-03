package com.example.spring_core_task.data;

import com.example.spring_core_task.model.Trainee;
import com.example.spring_core_task.model.Trainer;
import com.example.spring_core_task.model.Training;
import com.example.spring_core_task.model.TrainingType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataStorageDeserializationTest {

    private DataStorageDeserialization deserialization;

    @BeforeEach
    void setUp() {
        deserialization = new DataStorageDeserialization();
    }

    @Test
    void testTraineeDeserialize() throws IOException, ParseException {
        String json = "{\n" +
                "  \"trainees\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"firstName\": \"John\",\n" +
                "      \"lastName\": \"Doe\",\n" +
                "      \"isActive\": true,\n" +
                "      \"dateOfBirth\": \"2000-01-01\",\n" +
                "      \"address\": \"123 Main St\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"trainers\": [],\n" +
                "  \"trainings\": []\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode trainees = objectMapper.readTree(json).get("trainees");
        Map<Long, Trainee> traineeStorage = deserialization.traineesDeserialization(trainees);
        assertEquals(1, traineeStorage.size());
        assertEquals("John", traineeStorage.get(1L).getFirstName());
        assertEquals("Doe", traineeStorage.get(1L).getLastName());
        assertEquals("123 Main St", traineeStorage.get(1L).getAddress());
        assertEquals(true, traineeStorage.get(1L).isActive());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01"), traineeStorage.get(1L).getDateOfBirth());
    }
    @Test
    void testTrainerDeserialize() throws IOException {
        String json = "{\n" +
                "  \"trainees\": [],\n" +
                "  \"trainers\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"firstName\": \"Alice\",\n" +
                "      \"lastName\": \"Smith\",\n" +
                "      \"isActive\": true,\n" +
                "      \"specialization\": \"STRENGTH\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"trainings\": []\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode trainers = objectMapper.readTree(json).get("trainers");
        Map<Long, Trainer> trainerStorage = new DataStorageDeserialization().trainerDeserialization(trainers);

        assertEquals(1, trainerStorage.size());
        assertEquals("Alice", trainerStorage.get(1L).getFirstName());
        assertEquals("Smith", trainerStorage.get(1L).getLastName());
        assertEquals(true, trainerStorage.get(1L).isActive());
        assertEquals(TrainingType.STRENGTH, trainerStorage.get(1L).getSpecialization());
    }

    @Test
    void testTrainingDeserialize() throws IOException, ParseException {
        String json = "{\n" +
                "  \"trainees\": [],\n" +
                "  \"trainers\": [],\n" +
                "  \"trainings\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"trainerId\": 1,\n" +
                "      \"traineeId\": 1,\n" +
                "      \"startTime\": \"03-01 10:00\",\n" +
                "      \"duration\": \"PT1H\",\n" +
                "      \"trainingType\": \"STRENGTH\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode trainings = objectMapper.readTree(json).get("trainings");
        Map<Long, Training> trainingStorage = new DataStorageDeserialization().trainingDeserialization(trainings);

        assertEquals(1, trainingStorage.size());
        assertEquals(1L, trainingStorage.get(1L).getId());
        assertEquals(1L, trainingStorage.get(1L).getTrainerId());
        assertEquals(1L, trainingStorage.get(1L).getTraineeId());
        assertEquals(new SimpleDateFormat("MM-dd HH:mm").parse("03-01 10:00"), trainingStorage.get(1L).getTrainingDate());
        assertEquals(Duration.parse("PT1H"), trainingStorage.get(1L).getTrainigDuration());
        assertEquals(TrainingType.STRENGTH, trainingStorage.get(1L).getTrainingType());
    }
}
