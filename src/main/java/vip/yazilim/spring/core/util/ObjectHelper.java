package vip.yazilim.spring.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Helper methods for handling objects
 * 
 * @author Emre Sen - Dec 7, 2019
 * @contact maemresen@yazilim.vip
 *
 */
public class ObjectHelper {

	/**
	 * To convert input object to pretty JSON string
	 * 
	 * @param object input object
	 * @return JSON string
	 */
	public static final <T> String toPrettyJson(T object) {
		return toJson(object, true);
	}

	/**
	 * To convert input object to a JSON string
	 * 
	 * @param object input object
	 * @return JSON string
	 */
	public static final <T> String toJson(T object) {
		return toJson(object, false);
	}

	private static final <T> String toJson(T object, boolean indented) {
		if (object == null) {
			return "";
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			if (indented) {

				// pretty format
				return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			} else {

				// raw format
				return mapper.writeValueAsString(object);
			}
		} catch (JsonProcessingException e) {
			return object.toString();
		}
	}

}
