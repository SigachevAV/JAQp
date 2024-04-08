package com.example.JAQpApi.Entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T convertJsonToObject(String jsonString, Class<T> valueType) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, valueType);
    }

    // Other utility methods if needed
}