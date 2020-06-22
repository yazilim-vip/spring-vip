package vip.yazilim.libs.springvip.util

import org.slf4j.Logger

/**
 * @author Emre Sen - Dec 26, 2019
 * @contact maemresen@yazilim.vip
 */

fun traceException(logger: Logger, e: Exception, title: String = "Error") {
    logger.error("$title :: ", e)
}