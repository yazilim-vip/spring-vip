package vip.yazilim.libs.springvip.rest

/**
 * @author Emre Sen, 24.06.2020
 * @contact yazilim.vip
 */


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import vip.yazilim.libs.springvip.config.ARestConfig
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Generic REST Controller Implementations for generic Read operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
abstract class AGenericRestRead<E : Any, ID : Any>(restConfig: ARestConfig<E, ID>) : AGenericRest<E, ID>(restConfig) {

    // (R) read Operations
    @GetMapping("/")
    override fun restGetAll(request: HttpServletRequest, response: HttpServletResponse): Any {
        return super.restGetAll(request, response)
    }

    @GetMapping("/{id}")
    override fun restGetById(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): Any {
        return super.restGetById(request, response, id)
    }
}