package vip.yazilim.libs.springvip.exception

import kotlin.reflect.KClass


/**
 * Fired when a database exception occurred
 *
 * @author Emre Sen, 26.06.2019
 * @contact maemresen@yazilim.vip
 */
open class DatabaseException(entityClass: KClass<*>, type: String, description: String, exception: Exception)
    : RuntimeException(entityClass.simpleName + " :: " + type + " Error :: " + description, exception)

;
// SAVE
class DatabaseSaveException(entityClass: KClass<*>, id: Any, exception: Exception)
    : DatabaseException(entityClass, "SAVE", id.toString(), exception)

// create
class DatabaseCreateException(entityClass: KClass<*>, exception: Exception) : DatabaseException(entityClass, "CREATE", "", exception)

// READ
class DatabaseReadException : DatabaseException {
    constructor(entityClass: KClass<*>, id: Any, exception: Exception)
            : super(entityClass, "READ", id.toString(), exception)

    constructor(entityClass: KClass<*>, exception: Exception, vararg params: Any?)
            : super(entityClass, "READ", params.contentToString(), exception)
}

// UPDATE
class DatabaseUpdateException(entityClass: KClass<*>, id: Any, exception: Exception)
    : DatabaseException(entityClass, "UPDATE", id.toString(), exception)


// DELETE
class DatabaseDeleteException : DatabaseException {
    constructor(entityClass: KClass<*>, id: Any, exception: Exception)
            : super(entityClass, "DELETE", id.toString(), exception)

    constructor(entityClass: KClass<*>, exception: Exception, vararg params: Any?)
            : super(entityClass, "DELETE", params.contentToString(), exception)
}

// Rest API Call


class RestApiCallError(e: Exception) : RuntimeException("API call returned an error message", e)