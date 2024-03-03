package com.example.spring_core_task.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer {
    private final Resource dataResource;
    private final DataStorage dataStorage;

    public DataInitializer(@Value("${data.file.path}") Resource dataFilePath, DataStorage dataStorage) {
        this.dataResource = dataFilePath;
        this.dataStorage = dataStorage;
    }

    @PostConstruct
    public void init() {
        ObjectMapper objectMapper = dataStorage.getObjectMapper();
        try {
            DataStorage data = objectMapper.readValue(dataResource.getFile(), DataStorage.class);
            dataStorage.setTraineeStorage(data.getTraineeStorage());
            dataStorage.setTrainerStorage(data.getTrainerStorage());
            dataStorage.setTrainingStorage(data.getTrainingStorage());
        } catch (Exception e) {
            log.error("Failed to initialize data storage", e);
        }
    }
}
