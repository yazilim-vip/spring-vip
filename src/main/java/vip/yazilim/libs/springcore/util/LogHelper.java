package vip.yazilim.libs.springcore.util;

import org.slf4j.Logger;

/**
 * 
 * @author Emre Sen - Dec 26, 2019
 * @contact maemresen@yazilim.vip
 *
 */
public class LogHelper {

    public static void logException(Logger logger, Exception e) {

        StringBuffer logMessage = new StringBuffer();
        logMessage = logMessage.append("An error occurred.");

        // append exception cause
        Throwable cause = e.getCause();
        if (cause == null) {
            logMessage = logMessage.append("\n\tCause   :: " + cause);
        }

        // append exception message
        logMessage = logMessage.append("\n\tMessage :: " + e.getMessage());

        logger.error(logMessage.toString());
    }

    public static void traceException(Logger logger, String title, Exception e) {
        logger.error(title + " :: ", e);
    }
}
