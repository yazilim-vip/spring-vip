package vip.yazilim.libs.springvip.util.generic.rest.method

import vip.yazilim.libs.springvip.exception.*
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 22.08.2020
 */
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
    fun getById(request: HttpServletRequest, response: HttpServletResponse, id: ID): Any

}