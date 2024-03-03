package com.example.spring_core_task.data;

import com.example.spring_core_task.model.Trainee;
import com.example.spring_core_task.model.Trainer;
import com.example.spring_core_task.model.Training;
import com.example.spring_core_task.model.TrainingType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.SneakyThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DataStorageDeserialization extends JsonDeserializer<DataStorage> {
    private static final String DATE_OF_BIRTH_FORMAT = "yyyy-MM-dd";
    private static final String START_DATE_FORMAT = "MM-dd HH:mm";

    @SneakyThrows
    @Override
    public DataStorage deserialize(JsonParser parser, DeserializationContext context) {
        JsonNode jsonNode = parser.getCodec().readTree(parser);
        JsonNode trainees = jsonNode.get("trainees");
        Map<Long, Trainee> traineeStorage = traineesDeserialization(trainees);

        JsonNode trainers = jsonNode.get("trainers");
        Map<Long, Trainer> trainerStorage = trainerDeserialization(trainers);

        JsonNode trainings = jsonNode.get("trainings");
        Map<Long, Training> trainingStorage = trainingDeserialization(trainings);

        return new DataStorage(traineeStorage, trainerStorage, trainingStorage);
    }

    public Map<Long, Trainee> traineesDeserialization(JsonNode trainees) throws ParseException {
        Map<Long, Trainee> traineeStorage = new HashMap<>();
        if (trainees.isArray()) {
            for (JsonNode trainee : trainees) {
                Trainee t = new Trainee();
                t.setId(trainee.get("id").asLong());
                t.setFirstName(trainee.get("firstName").asText());
                t.setLastName(trainee.get("lastName").asText());
                t.setAddress(trainee.get("address").asText());
                t.setActive(trainee.get("isActive").asBoolean());
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_OF_BIRTH_FORMAT);
                t.setDateOfBirth(sdf.parse(trainee.get("dateOfBirth").asText()));
                t.setAddress(trainee.get("address").asText());

                traineeStorage.put(t.getId(), t);
            }
        }
        return traineeStorage;
    }

    public Map<Long, Trainer> trainerDeserialization(JsonNode trainers) {
        Map<Long, Trainer> trainerStorage = new HashMap<>();
        if (trainers.isArray()) {
            for (JsonNode trainer : trainers) {
                Trainer t = new Trainer();
                t.setId(trainer.get("id").asLong());
                t.setFirstName(trainer.get("firstName").asText());
                t.setLastName(trainer.get("lastName").asText());
                t.setActive(trainer.get("isActive").asBoolean());
                t.setSpecialization(TrainingType.valueOf(trainer.get("specialization").asText()));

                trainerStorage.put(t.getId(), t);
            }
        }
        return trainerStorage;
    }

    public Map<Long, Training> trainingDeserialization(JsonNode trainings) throws ParseException {
        Map<Long, Training> trainingStorage = new HashMap<>();
        if (trainings.isArray()) {
            for (JsonNode training : trainings) {
                Training t = new Training();
                t.setId(training.get("id").asLong());
                t.setTrainerId(training.get("trainerId").asLong());
                t.setTraineeId(training.get("traineeId").asLong());

                SimpleDateFormat sdf = new SimpleDateFormat(START_DATE_FORMAT);
                t.setTrainingDate(sdf.parse(training.get("startTime").asText()));
                t.setTrainigDuration(Duration.parse(training.get("duration").asText()));
                t.setTrainingType(TrainingType.valueOf(training.get("trainingType").asText()));

                trainingStorage.put(t.getId(), t);
            }
        }
        return trainingStorage;
    }
}
