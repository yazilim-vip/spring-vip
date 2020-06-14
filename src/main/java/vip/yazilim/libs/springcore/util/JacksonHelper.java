package vip.yazilim.libs.springcore.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Helper methods to use Jackson library
 *
 * @author Emre Sen - Dec 26, 2019
 * @contact maemresen@yazilim.vip
 */
public class JacksonHelper {

    public static String toJson(Object object, boolean indented) {
        if (object == null) {
            return "";
        }
        if (indented) {
            return toJson(object);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return object.toString();
        }
    }

    public static String toJson(Object object) {
        if (object == null) {
            return "";
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return object.toString();
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, typeReference);
    }
}
