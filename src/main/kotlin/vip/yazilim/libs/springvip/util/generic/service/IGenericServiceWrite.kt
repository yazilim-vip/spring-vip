package vip.yazilim.libs.springvip.util.generic.service

import vip.yazilim.libs.springvip.util.generic.service.exception.DatabaseCreateException
import vip.yazilim.libs.springvip.util.generic.service.exception.DatabaseSaveException
import vip.yazilim.libs.springvip.util.generic.service.exception.DatabaseUpdateException

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen (maemresen@yazilim.vip), 27.06.2019
 */
interface IGenericServiceWrite<E, ID> : IGenericServiceRead<E, ID> {


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
    fun save(entity: E): E

    // (C) create Operations
    /**
     * Insert Entity to the Data source.
     *
     * @param entity data to insert
     * @return inserted entity
     */
    @Throws(DatabaseCreateException::class)
    fun create(entity: E): E

    // (U) update Operations
    /**
     * Update table with given model
     *
     * @param newEntity new updated values to save into data source
     * @return saved entity to database
     */
    @Throws(DatabaseUpdateException::class)
    fun update(newEntity: E): E
}
