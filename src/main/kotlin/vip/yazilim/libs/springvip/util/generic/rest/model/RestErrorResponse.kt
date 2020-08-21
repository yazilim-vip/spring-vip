package vip.yazilim.libs.springvip.util.generic.rest.model

/**
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
class RestErrorResponse(timestamp: Long
                        , path: String
                        , message: String
                        , data: RestError<*>)
    : RestResponse<RestError<*>>(true, timestamp, path, message, data);
