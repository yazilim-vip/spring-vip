package vip.yazilim.libs.springvip.bean

import vip.yazilim.libs.springvip.util.generic.rest.AGenericRest
import vip.yazilim.libs.springvip.util.generic.rest.VipGenericRest

/**
 * A builder class to generate a Rest response for the Generic RestControllers
 *
 * @author maemresen - maemresen@yazilim.vip
 * 21.08.2020
 */
interface IGenericRestFactory {
    fun <E, ID, T : AGenericRest<E, ID>> buildProxyRestController(restControllerBean: T, vipGenericRest: VipGenericRest): Any
}