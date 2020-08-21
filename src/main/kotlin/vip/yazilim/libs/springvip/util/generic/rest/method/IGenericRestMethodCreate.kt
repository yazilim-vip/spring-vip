package vip.yazilim.libs.springvip.util.generic.rest.method

import vip.yazilim.libs.springvip.exception.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 22.08.2020
 */
interface IGenericRestMethodCreate<E, ID> {
    /**
     * Insert Entity to the Data source.
     *
     * @param entity data to insert
     * @return inserted entity
     */
    @Throws(DatabaseCreateException::class)
    fun create(request: HttpServletRequest, response: HttpServletResponse, entity: E): Any

    // (U) update Operations

}