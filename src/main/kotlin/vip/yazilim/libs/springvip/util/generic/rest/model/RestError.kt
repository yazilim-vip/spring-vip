package vip.yazilim.libs.springvip.util.generic.rest.model

/**
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
data class RestError<C>(private val errorCode: Int, private val errorCause: C)
