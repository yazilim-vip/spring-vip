package vip.yazilim.libs.springvip.util.generic.service.exception

import kotlin.reflect.KClass

// READ
class DatabaseReadException : DatabaseException {
    constructor(entityClass: KClass<*>, id: Any, exception: Exception)
            : super(entityClass, "READ", id.toString(), exception)

    constructor(entityClass: KClass<*>, exception: Exception, vararg params: Any?)
            : super(entityClass, "READ", params.contentToString(), exception)
}