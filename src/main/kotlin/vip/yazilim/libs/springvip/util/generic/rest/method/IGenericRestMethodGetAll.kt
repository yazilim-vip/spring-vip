package vip.yazilim.libs.springvip.util.generic.rest.method

import vip.yazilim.libs.springvip.exception.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 22.08.2020
 */
interface IGenericRestMethodGetAll<E, ID> {

    /**
     * Get all entities on the table
     *
     * @return list of entities
     */
    @Throws(DatabaseReadException::class)
    fun getAll(request: HttpServletRequest, response: HttpServletResponse): List<E>

}