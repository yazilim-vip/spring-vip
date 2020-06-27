package vip.yazilim.libs.springvip.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

abstract class SpringVipConfig {

    abstract fun restResponseGenerator(responseBody: Any
                                       , httpStatus: HttpStatus
                                       , request: HttpServletRequest
                                       , response: HttpServletResponse): Any
}

