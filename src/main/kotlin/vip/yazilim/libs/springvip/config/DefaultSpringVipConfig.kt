package vip.yazilim.libs.springvip.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.HandlerMapping
import vip.yazilim.libs.springvip.ext.logger
import vip.yazilim.libs.springvip.rest.model.RestResponse
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
open class DefaultSpringVipConfig : SpringVipConfig() {
    init {
        logger().debug("Using Default SpringVIP Configuration")
    }
}