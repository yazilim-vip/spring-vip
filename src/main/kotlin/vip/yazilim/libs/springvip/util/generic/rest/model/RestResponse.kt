package vip.yazilim.libs.springvip.util.generic.rest.model

/**
 * Generic Response model for REST Microservices.
 *
 * @author Emre Sen (maemresen@yazilim.vip), 24.07.2019
 */
open class RestResponse<E>(val hasError: Boolean = false
                           , val timestamp: Long
                           , val path: String
                           , val message: String
                           , val data: E)
