package vip.yazilim.libs.springvip.bean

import org.springframework.http.HttpStatus
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * A builder class to generate a Rest response for the Generic RestControllers
 *
 * @author maemresen - maemresen@yazilim.vip
 * 21.08.2020
 */
interface IRestResponseBuilder {

    fun generateRestResponse(responseBody: Any
                             , httpStatus: HttpStatus
                             , request: HttpServletRequest
                             , response: HttpServletResponse): Any
}