package vip.yazilim.libs.springvip.config

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import vip.yazilim.libs.springvip.config.bean.IRestResponseBuilder
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 1/1/2021
 */
@Component
class CustomResponseBuilder : IRestResponseBuilder {
    override fun generateRestResponse(
        responseBody: Any,
        httpStatus: HttpStatus,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Any {
        return responseBody
    }
}