package org.ssaad.ami.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtils {

    private final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static String toJson(Object object) {
        String json = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Failed to convert object into JSON string: {}", e.getMessage(), e);

        }
        return json;
    }

    public static <T> T toObject(String json, Class<T> classOfT) {
        T object = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            object = mapper.readValue(json, classOfT);
        } catch (IOException e) {
            logger.error("Failed to convert JSON Sting into Object: {}", e.getMessage(), e);
        }
        return object;
    }
}
