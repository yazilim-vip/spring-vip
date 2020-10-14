package vip.yazilim.libs.springvip.util.generic.service.method

import vip.yazilim.libs.springvip.exception.*

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 22.08.2020
 */
interface IGenericServiceMethodDeleteAll<E, ID> {
    /**
     * @return
     * @throws DatabaseDeleteException
     */
    @Throws(DatabaseDeleteException::class)
    fun deleteAll(): Boolean
}