package vip.yazilim.libs.springvip.service

import vip.yazilim.libs.springvip.exception.*
import java.util.*

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen, 27.06.2019
 * @contact maemresen@yazilim.vip
 */
interface IGenericServiceCrud<E, ID> : IGenericServiceRead<E, ID>, IGenericServiceWrite<E, ID>, IGenericServiceDelete<E, ID>

interface IGenericServiceRead<E, ID> : IGenericServiceMethodGetAll<E, ID>, IGenericServiceMethodGetById<E, ID>

interface IGenericServiceDelete<E, ID> : IGenericServiceMethodDeleteById<E, ID>, IGenericServiceMethodDelete<E, ID>, IGenericServiceMethodDeleteAll<E, ID>

interface IGenericServiceWrite<E, ID> : IGenericServiceMethodSave<E, ID>, IGenericServiceMethodCreate<E, ID>, IGenericServiceMethodUpdate<E, ID>

interface IGenericServiceMethodSave<E, ID> {
    /**
     * Save Entity to the Data source.
     *
     *
     * Insert if not exists
     *
     *
     * Update if exists
     *
     * @param entity data to insert
     * @return inserted entity
     */
    @Throws(IllegalArgumentException::class, DatabaseSaveException::class)
    fun save(entity: E, id: ID?): E

    // (C) create Operations

}

interface IGenericServiceMethodCreate<E, ID> {
    /**
     * Insert Entity to the Data source.
     *
     * @param entity data to insert
     * @return inserted entity
     */
    @Throws(DatabaseCreateException::class)
    fun create(entity: E): E

    // (U) update Operations

}

interface IGenericServiceMethodUpdate<E, ID> {
    /**
     * Update table with given model
     *
     * @param newEntity new updated values to save into data source
     * @return saved entity to database
     */
    @Throws(DatabaseUpdateException::class)
    fun update(newEntity: E): E

    // (R) read Operations

    /**
     * Get all entities on the table
     *
     * @return list of entities
     */

}

interface IGenericServiceMethodGetAll<E, ID> {
    @Throws(DatabaseReadException::class)
    fun getAll(): List<E>

}

interface IGenericServiceMethodGetById<E, ID> {
    /**
     * Get an entity from table by id.
     *
     * @param id id field of entity
     * @return entity with id
     * @throws DatabaseReadException    indicates an error in JPARepository
     * @throws IllegalArgumentException id given as argument is null
     */
    @Throws(DatabaseReadException::class)
    fun getById(id: ID): Optional<E>

    // (D) delete Operations

}

interface IGenericServiceMethodDeleteById<E, ID> {
    /**
     * @param id
     * @return
     * @throws DatabaseDeleteException
     */
    @Throws(DatabaseDeleteException::class)
    fun deleteById(id: ID): Boolean

}

interface IGenericServiceMethodDelete<E, ID> {
    /**
     * @param entity
     * @return
     * @throws DatabaseDeleteException
     */
    @Throws(DatabaseDeleteException::class)
    fun delete(entity: E): Boolean

}

interface IGenericServiceMethodDeleteAll<E, ID> {
    /**
     * @return
     * @throws DatabaseDeleteException
     */
    @Throws(DatabaseDeleteException::class)
    fun deleteAll(): Boolean
}