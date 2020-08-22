package vip.yazilim.libs.springvip.util.generic.service.method

import vip.yazilim.libs.springvip.exception.*

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 22.08.2020
 */
interface IGenericServiceMethodDeleteById<E, ID> {
    /**
     * @param id
     * @return
     * @throws DatabaseDeleteException
     */
    @Throws(DatabaseDeleteException::class)
    fun deleteById(id: ID): Boolean

}