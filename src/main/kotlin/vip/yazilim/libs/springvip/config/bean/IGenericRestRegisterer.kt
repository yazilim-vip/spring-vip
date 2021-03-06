package vip.yazilim.libs.springvip.config.bean

import vip.yazilim.libs.springvip.util.generic.rest.AGenericRest
import vip.yazilim.libs.springvip.util.generic.rest.GenericMethodType
import vip.yazilim.libs.springvip.util.generic.rest.GenericRest

/**
 * A builder class to generate a Rest response for the Generic RestControllers
 *
 * @author maemresen - maemresen@yazilim.vip
 * 21.08.2020
 */
interface IGenericRestRegisterer {
    fun <E, ID, T : AGenericRest<E, ID>> registerMappings(restControllerBean: T, genericRest: GenericRest, proxyRestController: Any, uriValue: String)
}