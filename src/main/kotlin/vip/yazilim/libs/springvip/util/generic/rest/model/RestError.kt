package vip.yazilim.libs.springvip.util.generic.rest.model

/**
 * The model that represents the error
 *
 * @author Emre Sen - maemresen@yazilim.vip
 * 24.07.2019
 */
data class RestError<C>(private val errorCode: Int, private val errorCause: C)
