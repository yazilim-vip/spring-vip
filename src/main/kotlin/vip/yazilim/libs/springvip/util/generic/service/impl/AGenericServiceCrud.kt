package vip.yazilim.libs.springvip.util.generic.service.impl

import org.springframework.data.jpa.repository.JpaRepository
import vip.yazilim.libs.springvip.exception.*
import vip.yazilim.libs.springvip.util.generic.service.IGenericServiceCrud
import vip.yazilim.libs.springvip.util.generic.service.IGenericServiceWrite
import java.util.*
import kotlin.reflect.KClass

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 22.08.2020
 */
abstract class AGenericServiceCrud<E : Any, ID : Any>(
        repository: JpaRepository<E, ID>,
        classOfEntity: KClass<E>,
        classOfId: KClass<ID>
) : AGenericServiceWrite<E, ID>(repository, classOfEntity, classOfId), IGenericServiceCrud<E, ID> {
    constructor(repository: JpaRepository<E, ID>, classOfEntity: Class<E>, classOfId: Class<ID>)
            : this(repository, classOfEntity.kotlin, classOfId.kotlin)

    @Throws(DatabaseDeleteException::class)
    override fun deleteById(id: ID): Boolean {
        return try {
            // delete entity
            repository.deleteById(id)
            true
        } catch (exception: Exception) {
            throw DatabaseDeleteException(classOfEntity, exception)
        }
    }

    @Throws(DatabaseDeleteException::class)
    override fun delete(entity: E): Boolean {
        return try {
            // delete entity
            repository.delete(entity)
            true
        } catch (exception: Exception) {
            throw DatabaseDeleteException(classOfEntity, exception)
        }
    }

    @Throws(DatabaseDeleteException::class)
    override fun deleteAll(): Boolean {
        return try {
            // delete entity
            repository.deleteAll()
            true
        } catch (exception: Exception) {
            throw DatabaseDeleteException(classOfEntity, exception)
        }
    }
}
