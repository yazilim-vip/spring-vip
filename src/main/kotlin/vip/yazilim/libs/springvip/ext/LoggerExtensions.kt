package vip.yazilim.libs.springvip.ext

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import vip.yazilim.libs.springvip.util.buildLogMsg

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 17.10.2020
 */
inline fun <reified T> T.logger(): Logger {
    if (T::class.isCompanion) {
        return LoggerFactory.getLogger(T::class.java.enclosingClass)
    }
    return LoggerFactory.getLogger(T::class.java)
}

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 17.10.2020
 */
inline fun <reified T> T.libLogError(msg: String, ex: Throwable? = null) {
    if (ex == null) {
        logger().error(buildLogMsg(msg))
    } else {
        logger().error(buildLogMsg(msg), ex)
    }
}

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 17.10.2020
 */
inline fun <reified T> T.libLogWarn(msg: String, ex: Throwable? = null) {
    if (ex == null) {
        logger().warn(buildLogMsg(msg))
    } else {
        logger().warn(buildLogMsg(msg), ex)
    }
}

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 17.10.2020
 */
inline fun <reified T> T.libLogInfo(msg: String) {
    logger().info(buildLogMsg(msg))
}

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 17.10.2020
 */
inline fun <reified T> T.libLogDebug(msg: String) {
    logger().debug(buildLogMsg(msg))
}

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 17.10.2020
 */
inline fun <reified T> T.libLogTrace(msg: String) {
    logger().trace(buildLogMsg(msg))
}