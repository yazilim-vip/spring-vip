package vip.yazilim.libs.springvip.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import vip.yazilim.libs.springvip.ext.logger

@Qualifier("springVipConfigurerAdapter")
abstract class SpringVipConfig {
}

@Configuration
open class DefaultSpringVipConfig() : SpringVipConfig() {

    init {
        logger().debug("Using Default SpringVIP Configuration")
    }
}