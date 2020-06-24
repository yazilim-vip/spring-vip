package vip.yazilim.libs.springvip.rest.model

import lombok.Data
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.HandlerMapping
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Emre Sen, 24.06.2020
 * @contact yazilim.vip
 */


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

/**
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
class RestErrorResponse(timestamp: Long
                        , path: String
                        , message: String
                        , data: RestError<*>)
    : RestResponse<RestError<*>>(true, timestamp, path, message, data);

/**
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
data class RestError<C>(private val errorCode: Int, private val errorCause: C)


fun <B> generateResponse(responseBody: B
                         , httpStatus: HttpStatus
                         , request: HttpServletRequest
                         , response: HttpServletResponse): RestResponse<B> {
    val path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) as String
    val restResponse: RestResponse<B> = RestResponse(
            timestamp = Date().time,
            path = path,
            message = httpStatus.reasonPhrase,
            data = responseBody
    )
    response.setIntHeader("status", httpStatus.value())
    return restResponse
}