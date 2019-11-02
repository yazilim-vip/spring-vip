package vip.yazilim.spring.utils.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectHelper {

	public static final String toJson(Object object) {
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

	public static final String toJson(Object object, boolean indented) {
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

}
