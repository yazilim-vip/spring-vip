package vip.yazilim.libs.springvip.util.generic.rest.method

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import vip.yazilim.libs.springvip.exception.*
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 22.08.2020
 */
@RequestMapping("/default")
interface IGenericRestMethodGetById<E, ID> {
    /**
     * Get an entity from table by id.
     *
     * @param id id field of entity
     * @return entity with id
     * @throws DatabaseReadException    indicates an error in JPARepository
     * @throws IllegalArgumentException id given as argument is null
     */
    @Throws(DatabaseReadException::class)
    @GetMapping("/{id}")
    fun getById(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): Any

}