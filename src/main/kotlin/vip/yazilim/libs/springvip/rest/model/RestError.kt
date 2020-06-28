package vip.yazilim.libs.springvip.rest.model

/**
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
data class RestError<C>(private val errorCode: Int, private val errorCause: C)
