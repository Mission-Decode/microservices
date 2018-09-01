package com.mail.utilities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CustomMapper extends ObjectMapper {
    private static ObjectMapper inst = null;
    private static final Logger logger = LoggerFactory.getLogger(CustomMapper.class);

    static {
        inst = new ObjectMapper();
        inst.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private CustomMapper() {

    }

    public static String objectToJson(Object object) {
        String value = null;
        try {
            value = inst.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("[object-mapper] Could not parse" + e.getMessage());
        }
        return value;
    }

    public static <T extends Object> T jsonToObject(String value, Class<T> classz) {
        T result = null;
        try {
            result = inst.readValue(value, classz);
        } catch (IOException e) {
            logger.error("[object-mapper] Could not parse" + e.getMessage());
        }
        return result;
    }

}
