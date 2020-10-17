package vip.yazilim.libs.springvip.util.generic.rest

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 16.10.2020
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class GenericRest(
        val methods: Array<GenericCrudMethods> = [],
        val name: String = ""
)