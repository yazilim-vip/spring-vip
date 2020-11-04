package vip.yazilim.libs.springvip.util.generic.service.exception

import kotlin.reflect.KClass

// DELETE
class DatabaseDeleteException : DatabaseException {
    constructor(entityClass: KClass<*>, id: Any, exception: Exception)
            : super(entityClass, "DELETE", id.toString(), exception)

    constructor(entityClass: KClass<*>, exception: Exception, vararg params: Any?)
            : super(entityClass, "DELETE", params.contentToString(), exception)
}