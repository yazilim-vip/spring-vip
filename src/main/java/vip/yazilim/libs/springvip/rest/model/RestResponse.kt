package vip.yazilim.libs.springvip.rest.model

import lombok.Data
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.HandlerMapping
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Generic Response model for REST Microservices.
 *
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
@Data
open class RestResponse<E> @JvmOverloads constructor(private val hasError: Boolean = false) {
    private val timestamp: Long = 0
    private val path: String? = null
    private val message: String? = null
    private val data: E? = null

    companion object {
        fun <B> generateResponse(responseBody: B?, httpStatus: HttpStatus, request: HttpServletRequest,
                                 response: HttpServletResponse): RestResponse<B> {
            val path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) as String
            val restResponse = RestResponse<B>(false)
            restResponse.setTimestamp(Date().time)
            restResponse.setPath(path)
            restResponse.setMessage(httpStatus.reasonPhrase)
            restResponse.setData(responseBody)
            response.setIntHeader("status", httpStatus.value())
            return restResponse
        }
    }

}