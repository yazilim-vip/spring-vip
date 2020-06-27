package vip.yazilim.libs.springvip.rest

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import vip.yazilim.libs.springvip.config.ARestConfig
import vip.yazilim.libs.springvip.config.IRestResponseGenerator
import vip.yazilim.libs.springvip.config.SpringVipConfig
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Generic REST Controller Implementations for generic CRUD operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
abstract class ARestCrud<E : Any, ID>(private val restConfig: ARestConfig<E, ID>) : ARestCru<E, ID>(restConfig) {
    // (D) delete Operations
    @DeleteMapping("/{id}")
    fun delete(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): Any {
        return restDelete(restConfig = restConfig
                , request = request
                , response = response
                , id = id)
    }

}