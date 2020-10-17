package vip.yazilim.libs.springvip.bean

import org.springframework.http.HttpStatus
import vip.yazilim.libs.springvip.util.generic.rest.VipGenericRest
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass

/**
 * A builder class to generate a Rest response for the Generic RestControllers
 *
 * @author maemresen - maemresen@yazilim.vip
 * 21.08.2020
 */
interface IGenericRestFactory {
    fun buildProxyRestController(restControllerBean: Any, vipGenericRest: VipGenericRest): Any
}