package vip.yazilim.libs.springvip.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import vip.yazilim.libs.springvip.bean.impl.GenericRestControllerLoader
import vip.yazilim.libs.springvip.bean.IRestResponseBuilder
import vip.yazilim.libs.springvip.bean.impl.DefaultRestResponseBuilder

/**
 * Library AutoConfiguration Class
 *
 * @author maemresen - maemresen@yazilim.vip
 * 21.08.2020
 */
@Configuration
open class SpringVipAutoConfiguration(
        private val genericRestControllerLoader: GenericRestControllerLoader,
) {

    @Bean
    @ConditionalOnMissingBean
    open fun restResponseBuilder(): IRestResponseBuilder {
        return DefaultRestResponseBuilder()
    }
}