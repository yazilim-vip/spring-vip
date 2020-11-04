package vip.yazilim.libs.springvip.util.generic.rest


// Rest API Call
open class RestApiCallError(e: Exception) : RuntimeException("API call returned an error message", e)