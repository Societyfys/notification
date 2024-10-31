package com.societyfy.notification.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import java.util.Map;
import java.util.UUID;

public interface Utils {

    /**
     * Converts an object to a Map with snake_case keys.
     *
     * @param object The object to convert.
     * @return A Map representation of the object with snake_case keys.
     */
    static Map<String, Object> convertToSnakeCaseMap(Object object) {
        ObjectMapper objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        return objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {
        });
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }

}
