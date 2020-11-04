package vip.yazilim.libs.springvip.util.generic.service.exception

import kotlin.reflect.KClass

// UPDATE
class DatabaseUpdateException(entityClass: KClass<*>, id: Any, exception: Exception)
    : DatabaseException(entityClass, "UPDATE", id.toString(), exception)