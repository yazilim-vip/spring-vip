package vip.yazilim.libs.springvip.config

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerMapping
import vip.yazilim.libs.springvip.rest.model.RestResponse
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface IRestResponseGenerator {

    fun generateResponse(responseBody: Any
                         , httpStatus: HttpStatus
                         , request: HttpServletRequest
                         , response: HttpServletResponse): Any

}

@Component
open class DefaultRestResponseGenerator : IRestResponseGenerator {

    override fun generateResponse(responseBody: Any, httpStatus: HttpStatus, request: HttpServletRequest, response: HttpServletResponse): RestResponse<Any> {
        val path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) as String
        val restResponse: RestResponse<Any> = RestResponse(
                timestamp = Date().time,
                path = path,
                message = httpStatus.reasonPhrase,
                data = responseBody
        )
        response.setIntHeader("status", httpStatus.value())
        return restResponse
    }
}
