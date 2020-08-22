package vip.yazilim.libs.springvip.util.generic.rest.method

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import vip.yazilim.libs.springvip.exception.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 22.08.2020
 */
@RequestMapping("/default")
interface IGenericRestMethodCreate<E, ID> {
    /**
     * Insert Entity to the Data source.
     *
     * @param entity data to insert
     * @return inserted entity
     */
    @Throws(DatabaseCreateException::class)
    @PostMapping("/")
    fun create(request: HttpServletRequest, response: HttpServletResponse, @Valid @RequestBody entity: E): Any

    // (U) update Operations

}