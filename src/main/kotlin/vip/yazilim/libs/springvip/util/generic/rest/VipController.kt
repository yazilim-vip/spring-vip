package vip.yazilim.libs.springvip.util.generic.rest

import kotlin.reflect.KClass

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 16.10.2020
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class VipController(val entityClass: KClass<*>)