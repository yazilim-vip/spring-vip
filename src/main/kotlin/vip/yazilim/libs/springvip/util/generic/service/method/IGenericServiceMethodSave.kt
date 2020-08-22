package vip.yazilim.libs.springvip.util.generic.service.method

import vip.yazilim.libs.springvip.exception.*

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 22.08.2020
 */
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
    fun save(entity: E): E

    // (C) create Operations

}
