package vip.yazilim.libs.springvip.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import vip.yazilim.libs.springvip.bean.ISpringVipHttpRestConfig
import vip.yazilim.libs.springvip.bean.defaults.DefaultSpringVipHttpRestConfig

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 21.08.2020
 */
@Configuration
open class SpringVipAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    open fun springVipHttpRestConfig(): ISpringVipHttpRestConfig {
        return DefaultSpringVipHttpRestConfig()
    }

}