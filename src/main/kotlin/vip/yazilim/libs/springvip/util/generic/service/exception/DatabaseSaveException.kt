package vip.yazilim.libs.springvip.util.generic.service.exception

import kotlin.reflect.KClass

// SAVE
class DatabaseSaveException(entityClass: KClass<*>, id: Any?, exception: Exception)
    : DatabaseException(entityClass, "SAVE", id.toString(), exception)