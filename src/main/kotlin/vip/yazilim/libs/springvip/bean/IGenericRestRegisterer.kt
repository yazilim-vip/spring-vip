package vip.yazilim.libs.springvip.bean

import vip.yazilim.libs.springvip.util.generic.rest.VipGenericRest
import kotlin.reflect.KClass

/**
 * A builder class to generate a Rest response for the Generic RestControllers
 *
 * @author maemresen - maemresen@yazilim.vip
 * 21.08.2020
 */
interface IGenericRestRegisterer {
    fun registerMappings(restControllerBeanClass: KClass<*>, proxyRestController: Any, vipGenericRest: VipGenericRest)
}