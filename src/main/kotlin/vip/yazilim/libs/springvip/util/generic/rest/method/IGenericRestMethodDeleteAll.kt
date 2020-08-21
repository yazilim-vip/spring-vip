package vip.yazilim.libs.springvip.util.generic.rest.method

import vip.yazilim.libs.springvip.exception.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 22.08.2020
 */
interface IGenericRestMethodDeleteAll<E, ID> {
    /**
     * @return
     * @throws DatabaseDeleteException
     */
    @Throws(DatabaseDeleteException::class)
    fun deleteAll(request: HttpServletRequest, response: HttpServletResponse): Any
}