package vip.yazilim.libs.springvip.util.generic.rest.method

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import vip.yazilim.libs.springvip.exception.DatabaseDeleteException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 22.08.2020
 */
interface IGenericRestMethodDeleteById<E, ID> {
    /**
     * @param id
     * @return
     * @throws DatabaseDeleteException
     */
    @Throws(DatabaseDeleteException::class)
    @DeleteMapping("/{id}")
    fun deleteById(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): Any

}