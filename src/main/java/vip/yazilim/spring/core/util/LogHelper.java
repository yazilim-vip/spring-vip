package vip.yazilim.spring.core.util;

import org.slf4j.Logger;

/**
 * Helper methods for logging
 *
 * @author Emre Sen - Dec 7, 2019
 * @contact maemresen@yazilim.vip
 *
 */
public class LogHelper {

    /**
     * To log exception with its causes and message itself
     *
     * @param logger
     * @param e
     */
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

    /**
     * To trace all details of the given exception
     *
     * @param logger
     * @param e
     */
    public static void traceException(Logger logger, Exception e) {
        logger.error("Error ::", e);
    }

}
