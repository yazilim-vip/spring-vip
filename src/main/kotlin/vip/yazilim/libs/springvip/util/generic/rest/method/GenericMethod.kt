package vip.yazilim.libs.springvip.util.generic.rest.method

import org.springframework.web.bind.annotation.RequestMethod
import vip.yazilim.libs.springvip.util.generic.rest.GenericMethodType

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 21.10.2020
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class GenericMethod(
        val httpMethod: RequestMethod,
        val genericMethodType: GenericMethodType,
        val uri: String = ""
)