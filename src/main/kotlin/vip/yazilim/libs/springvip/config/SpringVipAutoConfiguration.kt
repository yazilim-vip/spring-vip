package vip.yazilim.libs.springvip.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.context.annotation.Configuration
import org.springframework.core.type.filter.AnnotationTypeFilter
import vip.yazilim.libs.springvip.bean.IGenericRestControllerFactory
import vip.yazilim.libs.springvip.bean.IGenericRestControllerRegisterer
import vip.yazilim.libs.springvip.bean.IRestResponseBuilder
import vip.yazilim.libs.springvip.bean.defaults.DefaultRestResponseBuilder
import vip.yazilim.libs.springvip.util.generic.rest.VipGenericRest

/**
 * Library AutoConfiguration Class
 *
 * @author maemresen - maemresen@yazilim.vip
 * 21.08.2020
 */
@Configuration
open class SpringVipAutoConfiguration(
        context: ConfigurableApplicationContext,
        genericRestFactory: IGenericRestControllerFactory,
        genericRestRegisterer: IGenericRestControllerRegisterer,
) {

    init {
        val scanner = ClassPathScanningCandidateComponentProvider(false)
        scanner.addIncludeFilter(AnnotationTypeFilter(VipGenericRest::class.java))

        /*
        -------------------------------
        | FIND GENERIC REST CONTROLLER
        -------------------------------
        */
        for (beanDefinition in scanner.findCandidateComponents("vip.yazilim.libs.springvip")) {
            try {
                val beanClass = Class.forName(beanDefinition.beanClassName);
                if (beanClass.isAnnotationPresent(VipGenericRest::class.java)) {
                    val vipGenericRest = beanClass.getAnnotation(VipGenericRest::class.java);
                    val restControllerBean = context.getBean(beanClass)
                    val proxyRestController = genericRestFactory.buildProxyRestController(restControllerBean, vipGenericRest)
                    genericRestRegisterer.registerMappings(restControllerBean::class, proxyRestController, vipGenericRest)
                }
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            }
        }
    }

    @Bean
    @ConditionalOnMissingBean
    open fun springVipHttpRestConfig(): IRestResponseBuilder {
        return DefaultRestResponseBuilder()
    }
}