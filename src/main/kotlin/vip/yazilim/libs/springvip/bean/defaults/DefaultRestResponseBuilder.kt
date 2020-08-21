package vip.yazilim.libs.springvip.bean.defaults

import org.springframework.http.HttpStatus
import org.springframework.web.servlet.HandlerMapping
import vip.yazilim.libs.springvip.bean.IRestResponseBuilder
import vip.yazilim.libs.springvip.util.generic.rest.model.RestResponse
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class DefaultRestResponseBuilder : IRestResponseBuilder {

    override fun generateRestResponse(responseBody: Any, httpStatus: HttpStatus, request: HttpServletRequest, response: HttpServletResponse): Any {
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