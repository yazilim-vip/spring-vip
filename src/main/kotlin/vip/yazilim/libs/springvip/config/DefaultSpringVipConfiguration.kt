package vip.yazilim.libs.springvip.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.HandlerMapping
import vip.yazilim.libs.springvip.ext.logger
import vip.yazilim.libs.springvip.rest.model.RestResponse
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
open class DefaultSpringVipConfiguration() : SpringVipConfiguration() {

    init {
        logger().debug("Using Default SpringVIP Configuration")
    }

    override fun <B> generateResponse(responseBody: B
                                      , httpStatus: HttpStatus
                                      , request: HttpServletRequest
                                      , response: HttpServletResponse): RestResponse<*> {
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
}