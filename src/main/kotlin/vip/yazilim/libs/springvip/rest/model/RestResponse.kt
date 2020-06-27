package vip.yazilim.libs.springvip.rest.model

/**
 * Generic Response model for REST Microservices.
 *
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
open class RestResponse<E>(val hasError: Boolean = false
                           , val timestamp: Long
                           , val path: String
                           , val message: String
                           , val data: E)
