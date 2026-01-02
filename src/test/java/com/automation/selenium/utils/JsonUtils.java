package com.automation.selenium.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JsonUtils {
    private JsonUtils() {}

    public static JsonNode readJson(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(new File(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Unable to read JSON file", e);
        }
    }
}
