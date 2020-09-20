package vip.yazilim.libs.springvip.util.generic.rest.model

/**
 * The model that RestController used as a response when an error occurred
 *
 * @author Emre Sen - maemresen@yazilim.vip
 * 24.07.2019
 */
class RestErrorResponse(timestamp: Long
                        , path: String
                        , message: String
                        , data: RestError<*>)
    : RestResponse<RestError<*>>(true, timestamp, path, message, data);
