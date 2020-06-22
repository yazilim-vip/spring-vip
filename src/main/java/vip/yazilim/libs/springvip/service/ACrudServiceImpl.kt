package vip.yazilim.libs.springvip.service

import org.springframework.data.jpa.repository.JpaRepository
import vip.yazilim.libs.springvip.exception.*
import vip.yazilim.libs.springvip.util.ListHelper
import java.util.*

/**
 * Abstract Implementation of ICrudService.
 *
 *
 * Basic CRUD operations for an entity is implemented.
 *
 * @param <E>  type entity
 * @param <ID> type of identity of entity
 * @author Emre Sen, 19.07.2019
 * @contact maemresen@yazilim.vip
</ID></E> */
abstract class ACrudServiceImpl<E : Any, ID : Any> : ICrudService<E, ID> {
    /**
     * Repository of the entity that will be used with this implementation
     * @return JPARepository Instance
     */
    protected abstract val repository: JpaRepository<E, ID>
    protected abstract val classOfEntity: KClass<*>

    /**
     * Get Id of the entity
     *
     * @param entity input entity model
     * @return id of entity
     */
    protected abstract fun getId(entity: E): ID

    @Throws(DatabaseSaveException::class)
    override fun save(entity: E): E {
        return try {
            val repository: JpaRepository<E, ID> = repository
            val savedEntity = repository.save(entity) ?: throw NoSuchElementException("Saved entity not found")
            savedEntity
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
    protected fun preInsert(entity: E): E {
        return entity
    }

    @Throws(DatabaseUpdateException::class)
    override fun update(newEntity: E): E {
        return try {
            // get old entity
            val id = getId(newEntity)
            val oldEntity = getById(id)
            require(oldEntity.isPresent) { "Cannot update non-exist enity" }

            // prepare entity for update
            val preparedEntity = preUpdate(oldEntity.get(), newEntity)
            save(preparedEntity)
        } catch (exception: Exception) {
            throw DatabaseUpdateException(classOfEntity, getId(newEntity), exception)
        }
    }

    @get:Throws(DatabaseReadException::class)
    override val all: List<E>?
        get() = try {
            val repository = repository
            val resultList = repository.findAll()
            ListHelper.getSafeList(resultList)
        } catch (exception: Exception) {
            throw DatabaseReadException(classOfEntity, exception)
        }

    @Throws(DatabaseReadException::class)
    override fun getById(id: ID?): Optional<E?> {
        return try {
            val repository = repository
            requireNotNull(id) { "ID cannot be null" }

            // find entity by id
            repository.findById(id)
        } catch (exception: Exception) {
            throw DatabaseReadException(classOfEntity, exception)
        }
    }

    /**
     * Operations before update operation
     *
     * @param oldEntity old data on data source
     * @param newEntity new data that will be saved to data source
     * @return prepared entity to update
     */
    protected fun preUpdate(oldEntity: E?, newEntity: E?): E? {
        return newEntity
    }

    @Throws(DatabaseDeleteException::class)
    override fun deleteById(id: ID?): Boolean {
        return try {
            val repository = repository

            // delete entity
            repository.deleteById(id)
            true
        } catch (exception: Exception) {
            throw DatabaseDeleteException(classOfEntity, exception)
        }
    }

    @Throws(DatabaseDeleteException::class)
    override fun delete(entity: E?): Boolean {
        return try {
            val repository = repository

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
            val repository = repository

            // delete entity
            repository.deleteAll()
            true
        } catch (exception: Exception) {
            throw DatabaseDeleteException(classOfEntity, exception)
        }
    }
}