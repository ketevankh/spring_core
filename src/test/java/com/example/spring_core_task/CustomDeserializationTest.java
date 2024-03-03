package com.example.spring_core_task;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;

@Component
public class CustomDeserializationTest {
    @Autowired
    ObjectMapper objectMapper;
    @Value("${classpath:InitialData.json}")
    Resource initData;
    String json;
    @BeforeEach
    void setUp() throws IOException {
        json = new String(Files.readAllBytes(initData.getFile().toPath()));
        JsonNode jsonNode = objectMapper.readValue(json, JsonNode.class);
        jsonNode.get("TraineeStorage");
    }
}
