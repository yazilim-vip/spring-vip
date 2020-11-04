package vip.yazilim.libs.springvip.util.generic.service

import vip.yazilim.libs.springvip.util.generic.service.exception.DatabaseReadException
import java.util.*

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen (maemresen@yazilim.vip), 27.06.2019
 */
interface IGenericServiceRead<E, ID> {

    // (R) read Operations
    /**
     * Get all entities on the table
     *
     * @return list of entities
     */
    @Throws(DatabaseReadException::class)
    fun getAll(): List<E>

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

}
