package vip.yazilim.libs.springvip.util.generic.service.impl

import org.springframework.data.jpa.repository.JpaRepository
import vip.yazilim.libs.springvip.util.generic.service.IGenericServiceWrite
import vip.yazilim.libs.springvip.util.generic.service.exception.DatabaseCreateException
import vip.yazilim.libs.springvip.util.generic.service.exception.DatabaseSaveException
import vip.yazilim.libs.springvip.util.generic.service.exception.DatabaseUpdateException
import java.util.*
import kotlin.reflect.KClass

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 22.08.2020
 */
abstract class AGenericServiceWrite<E : Any, ID : Any>(
    repository: JpaRepository<E, ID>,
    classOfEntity: KClass<E>,
    classOfId: KClass<ID>
) : AGenericServiceRead<E, ID>(
    repository, classOfEntity, classOfId
), IGenericServiceWrite<E, ID> {

    constructor(repository: JpaRepository<E, ID>, classOfEntity: Class<E>, classOfId: Class<ID>)
            : this(repository, classOfEntity.kotlin, classOfId.kotlin)

    @Throws(DatabaseSaveException::class)
    override fun save(entity: E): E {
        return try {
            repository.save(entity) ?: throw NoSuchElementException("Saved entity not found")
        } catch (exception: Exception) {
            throw DatabaseSaveException(entity::class, getId(entity), exception)
        }
    }

    @Throws(DatabaseCreateException::class)
    override fun create(entity: E): E {
        return try {
            // initialize entity to insert
            // e.g setting unique UUID
            val initializedEntity = preInsert(entity)

            // get old entity
            val id = getId(initializedEntity)
            require(!getById(id).isPresent) { "Entity already exists" }

            save(initializedEntity)
        } catch (exception: Exception) {
            throw DatabaseCreateException(classOfEntity, exception)
        }
    }

    /**
     * Operations before insert entity to the table
     *
     *
     * E.g.: setting uuid value
     *
     * @param entity values that will be inserted
     * @return entity that inserted to the data source
     */
    protected open fun preInsert(entity: E): E {
        return entity
    }

    @Throws(DatabaseUpdateException::class)
    override fun update(newEntity: E): E {
        return try {
            // get old entity
            val id = getId(newEntity)
            val oldEntity = getById(id)
            require(oldEntity.isPresent) { "Cannot update non-exist entity" }

            // prepare entity for update
            val preparedEntity = preUpdate(oldEntity.get(), newEntity)
            save(preparedEntity)
        } catch (exception: Exception) {
            throw DatabaseUpdateException(classOfEntity, getId(newEntity), exception)
        }
    }

    /**
     * Operations before update operation
     *
     * @param oldEntity old data on data source
     * @param newEntity new data that will be saved to data source
     * @return prepared entity to update
     */
    protected open fun preUpdate(oldEntity: E, newEntity: E): E {
        return newEntity
    }

}
