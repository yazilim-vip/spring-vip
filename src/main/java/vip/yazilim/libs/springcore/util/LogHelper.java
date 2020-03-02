package vip.yazilim.libs.springcore.util;

import org.slf4j.Logger;

/**
 * @author Emre Sen - Dec 26, 2019
 * @contact maemresen@yazilim.vip
 */
public class LogHelper {
    
    public static void traceException(Logger logger, Exception e) {
        traceException(logger, e, "Error");
    }

    public static void traceException(Logger logger, Exception e, String title) {
        logger.error(title + " :: ", e);
    }
}
