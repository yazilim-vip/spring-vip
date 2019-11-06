package cloud.cantek.ms.core.util;

import org.slf4j.Logger;

import cloud.cantek.ms.core.exception.GeneralException;

public class LogHelper {

	public static void logException(Logger logger, GeneralException generalException) {

		StringBuffer logMessage = new StringBuffer();
		logMessage = logMessage.append("An error occurred.");

		// append exception cause
		Throwable cause = generalException.getCause();
		if (cause == null) {
			logMessage = logMessage.append("\n\tCause   :: " + cause);
		}

		// append exception message
		logMessage = logMessage.append("\n\tMessage :: " + generalException.getMessage());
		
		logger.error(logMessage.toString());
	}

}
