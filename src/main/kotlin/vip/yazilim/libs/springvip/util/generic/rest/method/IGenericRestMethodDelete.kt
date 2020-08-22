package vip.yazilim.libs.springvip.util.generic.rest.method

import org.springframework.web.bind.annotation.RequestMapping
import vip.yazilim.libs.springvip.exception.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 22.08.2020
 */
@RequestMapping("/default")
interface IGenericRestMethodDelete<E, ID> {
    /**
     * @param entity
     * @return
     * @throws DatabaseDeleteException
     */
    @Throws(DatabaseDeleteException::class)
    fun delete(request: HttpServletRequest, response: HttpServletResponse, entity: E): Any

}