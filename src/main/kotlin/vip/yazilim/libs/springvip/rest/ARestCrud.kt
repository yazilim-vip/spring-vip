package vip.yazilim.libs.springvip.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import vip.yazilim.libs.springvip.config.SpringVipConfiguration
import vip.yazilim.libs.springvip.rest.model.RestResponse
import vip.yazilim.libs.springvip.rest.model.generateResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Generic REST Controller Implementations for generic CRUD operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
abstract class ARestCrud<E : Any, ID>(private val springVipConfiguration: SpringVipConfiguration) : ARestCru<E, ID>(springVipConfiguration) {
    // (D) delete Operations
    @DeleteMapping("/{id}")
    fun delete(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): Any {
        return restDelete(springVipConfiguration = springVipConfiguration
                , restConfig = this
                , request = request
                , response = response
                , id = id)
    }

}