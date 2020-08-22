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
interface IGenericRestMethodSave<E, ID> {
    /**
     * Save Entity to the Data source.
     *
     *
     * Insert if not exists
     *
     *
     * Update if exists
     *
     * @param entity data to insert
     * @return inserted entity
     */
    @Throws(IllegalArgumentException::class, DatabaseSaveException::class)
    fun save(request: HttpServletRequest, response: HttpServletResponse, entity: E): Any


}
