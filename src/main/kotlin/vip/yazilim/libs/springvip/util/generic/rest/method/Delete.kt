package vip.yazilim.libs.springvip.util.generic.rest.method

import org.springframework.core.annotation.AliasFor
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
@GenericMethod(httpMethod = RequestMethod.DELETE, genericMethodType = GenericMethodType.DELETE)
annotation class Delete(
        @get:AliasFor(annotation = GenericMethod::class)
        val uri: String = "/"
)
