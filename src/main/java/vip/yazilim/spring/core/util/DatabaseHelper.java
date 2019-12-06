package vip.yazilim.spring.core.util;

import java.util.UUID;

/**
 * Helper methods to interact with Database
 * 
 * @author Emre Sen, 07.08.2019
 * @contact maemresen@yazilim.vip
 */
public class DatabaseHelper {

	/**
	 * Generate a random UUID value for entitites
	 * 
	 * @return random UUID value
	 */
	public static String randomUuidGenerator() {
		return UUID.randomUUID().toString();
	}

}
