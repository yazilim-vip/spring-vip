package vip.yazilim.libs.springvip.util.generic.rest

import org.springframework.web.bind.annotation.RequestMethod

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 16.10.2020
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class GenericRest(
        val name: String = ""
)

