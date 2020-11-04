package vip.yazilim.libs.springvip.util.generic.service.impl

import org.springframework.data.jpa.repository.JpaRepository
import vip.yazilim.libs.springvip.util.generic.service.IGenericServiceRead
import vip.yazilim.libs.springvip.util.generic.service.exception.DatabaseReadException
import java.util.*
import kotlin.reflect.KClass

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 22.08.2020
 */
abstract class AGenericServiceRead<E : Any, ID : Any>(
        protected val repository: JpaRepository<E, ID>,
        protected val classOfEntity: KClass<E>,
        protected val classOfId: KClass<ID>
) : IGenericServiceRead<E, ID> {
    constructor(repository: JpaRepository<E, ID>, classOfEntity: Class<E>, classOfId: Class<ID>)
            : this(repository, classOfEntity.kotlin, classOfId.kotlin)

    /**
     * Get Id of the entity
     *
     * @param entity input entity model
     * @return id of entity
     */
    protected abstract fun getId(entity: E): ID

    override fun getAll(): List<E> {
        return try {
            // find entity by id
            repository.findAll()
        } catch (exception: Exception) {
            throw DatabaseReadException(classOfEntity, exception)
        }
    }

    @Throws(DatabaseReadException::class)
    override fun getById(id: ID): Optional<E> {
        return try {
            // find entity by id
            repository.findById(id)
        } catch (exception: Exception) {
            throw DatabaseReadException(classOfEntity, exception)
        }
    }
}
