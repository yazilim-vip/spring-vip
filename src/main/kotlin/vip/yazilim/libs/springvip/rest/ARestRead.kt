package vip.yazilim.libs.springvip.rest

/**
 * @author Emre Sen, 24.06.2020
 * @contact yazilim.vip
 */


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import vip.yazilim.libs.springvip.config.SpringVipConfiguration
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Generic REST Controller Implementations for generic Read operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
abstract class ARestRead<E : Any, ID>(private val springVipConfiguration: SpringVipConfiguration) : ARestConfig<E, ID>(springVipConfiguration) {

    // (R) read Operations
    @GetMapping("/")
    fun getAll(request: HttpServletRequest, response: HttpServletResponse): Any {
        return restGetAll(springVipConfiguration = springVipConfiguration
                , restConfig = this
                , request = request
                , response = response)
    }

    @GetMapping("/{id}")
    fun getById(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): Any {
        return restGetById(springVipConfiguration = springVipConfiguration
                , restConfig = this
                , request = request
                , response = response
                , id = id)
    }
}