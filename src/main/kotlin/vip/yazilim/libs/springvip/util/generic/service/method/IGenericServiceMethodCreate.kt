package vip.yazilim.libs.springvip.util.generic.service.method

import vip.yazilim.libs.springvip.exception.*

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 22.08.2020
 */
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