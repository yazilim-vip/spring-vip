package vip.yazilim.libs.springvip.util.generic.rest.method

import vip.yazilim.libs.springvip.exception.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 22.08.2020
 */
interface IGenericRestMethodUpdate<E, ID> {
    /**
     * Update table with given model
     *
     * @param newEntity new updated values to save into data source
     * @return saved entity to database
     */
    @Throws(DatabaseUpdateException::class)
    fun update(request: HttpServletRequest, response: HttpServletResponse, newEntity: E): E

}