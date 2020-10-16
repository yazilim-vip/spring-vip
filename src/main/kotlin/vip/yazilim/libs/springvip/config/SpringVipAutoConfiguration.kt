package vip.yazilim.libs.springvip.config

import net.bytebuddy.ByteBuddy
import net.bytebuddy.description.annotation.AnnotationDescription
import net.bytebuddy.dynamic.DynamicType
import net.bytebuddy.implementation.MethodDelegation
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.context.annotation.Configuration
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import vip.yazilim.libs.springvip.bean.IRestResponseBuilder
import vip.yazilim.libs.springvip.bean.defaults.DefaultRestResponseBuilder
import vip.yazilim.libs.springvip.util.generic.VipGenericRest
import vip.yazilim.libs.springvip.util.generic.rest.AGenericRest
import java.lang.reflect.Modifier
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Library AutoConfiguration Class
 *
 * @author maemresen - maemresen@yazilim.vip
 * 21.08.2020
 */
@Configuration
open class SpringVipAutoConfiguration(
        private val handlerMapping: RequestMappingHandlerMapping,
        private val context: ConfigurableApplicationContext,
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
                val beanClass = Class.forName(beanDefinition.beanClassName) as Class<AGenericRest<*, *>>
                if (!beanClass.isAnnotationPresent(VipGenericRest::class.java)) {
                    // skip if bean does not have required annotation
                    continue
                }

                val vipGenericRest = beanClass.getAnnotation(VipGenericRest::class.java)
                //TODO: uri variables will be used
                val uriValues = if (beanClass.isAnnotationPresent(RequestMapping::class.java)) {
                    beanClass.getAnnotation(RequestMapping::class.java).value
                } else {
                    emptyArray();
                }

                /*
                -------------------------------
                | DEFINE NEW CONTROLLER CLASS
                -------------------------------
                 */
                var builder: DynamicType.Builder<Any?> = ByteBuddy()
                        .subclass(Any::class.java)
                        .name("${beanClass.simpleName}GenericController")
                        .annotateType(AnnotationDescription.Builder
                                .ofType(RestController::class.java) // don't use `request` mapping here
                                .build()
                        )

                /*
                -------------------------------
                | BEGIN DEFINE CUSTOM METHODS
                -------------------------------
                 */
                builder = builder.defineMethod("getAllGenericImpl", Any::class.java, Modifier.PUBLIC)
                        .withParameters(HttpServletRequest::class.java, HttpServletResponse::class.java)
                        .intercept(MethodDelegation.to(context.getBean(beanClass)))
                /*
                -------------------------------
                | END DEFINE CUSTOM METHODS
                -------------------------------
                 */

                /*
                -------------------------------
                | CREATE NEW CONTROLLER
                -------------------------------
                 */
                val userController: Any = builder.make()
                        .load(javaClass.classLoader)
                        .loaded
                        .newInstance()!!

                /*
                -------------------------------
                | REGISTER MAPPINGS
                -------------------------------
                 */
                handlerMapping.registerMapping(
                        RequestMappingInfo.paths("/emre/")
                                .methods(RequestMethod.GET)
                                .produces(MediaType.APPLICATION_JSON_VALUE)
                                .build(),
                        userController,
                        userController.javaClass.getMethod("getAllGenericImpl", HttpServletRequest::class.java, HttpServletResponse::class.java))
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