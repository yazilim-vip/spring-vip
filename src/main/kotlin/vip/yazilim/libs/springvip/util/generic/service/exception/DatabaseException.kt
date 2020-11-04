package vip.yazilim.libs.springvip.util.generic.service.exception

import kotlin.reflect.KClass

/**
 * Fired when a database exception occurred
 *
 * @author Emre Sen (maemresen@yazilim.vip), 26.06.2019
 */
open class DatabaseException(entityClass: KClass<*>, type: String, description: String, exception: Exception)
    : RuntimeException(entityClass.simpleName + "::" + type + " Error::" + description, exception)