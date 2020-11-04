package vip.yazilim.libs.springvip.util.generic.service.exception

import kotlin.reflect.KClass

// create
class DatabaseCreateException(entityClass: KClass<*>, exception: Exception) : DatabaseException(entityClass, "CREATE", "", exception)