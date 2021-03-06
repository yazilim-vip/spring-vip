package vip.yazilim.libs.springvip.config.bean

import vip.yazilim.libs.springvip.util.generic.rest.AGenericRest
import vip.yazilim.libs.springvip.util.generic.rest.GenericRest

/**
 * A builder class to generate a Rest response for the Generic RestControllers
 *
 * @author maemresen - maemresen@yazilim.vip
 * 21.08.2020
 */
interface IGenericRestFactory {
    fun <E, ID, T : AGenericRest<E, ID>> buildProxyRestController(restControllerBean: T, genericRest: GenericRest, uri: String): Any
}