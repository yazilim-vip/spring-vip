package vip.yazilim.libs.springvip.bean.impl

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import vip.yazilim.libs.springvip.bean.IGenericRestRegisterer
import vip.yazilim.libs.springvip.util.generic.rest.AGenericRest
import vip.yazilim.libs.springvip.util.generic.rest.GenericCrudMethods
import vip.yazilim.libs.springvip.util.generic.rest.GenericCrudMethods.*
import vip.yazilim.libs.springvip.util.generic.rest.VipGenericRest
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

/**
 * Default  RestResponse Builder Implementation
 */
@Component
class GenericRestRegisterer(
        private val handlerMapping: RequestMappingHandlerMapping,
) : IGenericRestRegisterer {

    override fun <E, ID, T : AGenericRest<E, ID>> registerMappings(restControllerBean: T, proxyRestController: Any, vipGenericRest: VipGenericRest) {
        val restControllerBeanClass = restControllerBean::class
        val uriValues = restControllerBeanClass.findAnnotation<RequestMapping>()?.value ?: emptyArray()
        uriValues.forEach { uriValue: String ->
            run {
                vipGenericRest.methods.forEach {
                    when (it) {
                        GET_ALL ->
                            handlerMapping.registerMapping( 
                                    RequestMappingInfo.paths("${uriValue}${it.uri}")
                                            .methods(it.httpMethod)
                                            .produces(MediaType.APPLICATION_JSON_VALUE)
                                            .build(),
                                    proxyRestController,
                                    proxyRestController.javaClass.getMethod(it.methodName, HttpServletRequest::class.java, HttpServletResponse::class.java))
                        GET_BY_ID ->
                            handlerMapping.registerMapping(
                                    RequestMappingInfo.paths("${uriValue}${it.uri}")
                                            .methods(it.httpMethod)
                                            .produces(MediaType.APPLICATION_JSON_VALUE)
                                            .build(),
                                    proxyRestController,
                                    proxyRestController.javaClass.getMethod(it.methodName, HttpServletRequest::class.java, HttpServletResponse::class.java, restControllerBean.classOfId.java))
                        CREATE -> TODO()
                        DELETE_ALL -> TODO()
                        DELETE_BY_ID -> TODO()
                        DELETE -> TODO()
                        SAVE -> TODO()
                        UPDATE -> TODO()
                    }
                }
            }

        }
    }
}