package vip.yazilim.libs.springvip.util.generic.service.method

import vip.yazilim.libs.springvip.exception.*

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 22.08.2020
 */
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