package vip.yazilim.libs.springvip.bean.impl

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import vip.yazilim.libs.springvip.bean.IGenericRestRegisterer
import vip.yazilim.libs.springvip.util.generic.rest.AGenericRest
import vip.yazilim.libs.springvip.util.generic.rest.GenericCrudMethods
import vip.yazilim.libs.springvip.util.generic.rest.GenericCrudMethods.*
import vip.yazilim.libs.springvip.util.generic.rest.GenericRest
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.full.findAnnotation

/**
 * Default  RestResponse Builder Implementation
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class GenericRestRegisterer(
        private val handlerMapping: RequestMappingHandlerMapping,
) : IGenericRestRegisterer {

    override fun <E, ID, T : AGenericRest<E, ID>> registerMappings(restControllerBean: T, genericRest: GenericRest, proxyRestController: Any, uriValue: String, methods: Array<GenericCrudMethods>) {
        methods.forEach {
            if (it == GET_ALL || it == DELETE_ALL) {
                handlerMapping.registerMapping(
                        RequestMappingInfo.paths("${uriValue}${it.uri}")
                                .methods(it.httpMethod)
                                .produces(MediaType.APPLICATION_JSON_VALUE)
                                .build(),
                        proxyRestController,
                        proxyRestController.javaClass.getMethod(it.methodName, HttpServletRequest::class.java, HttpServletResponse::class.java))
            } else if (it == CREATE || it == UPDATE || it == SAVE || it == DELETE) {
                handlerMapping.registerMapping(
                        RequestMappingInfo.paths("${uriValue}${it.uri}")
                                .methods(it.httpMethod)
                                .produces(MediaType.APPLICATION_JSON_VALUE)
                                .build(),
                        proxyRestController,
                        proxyRestController.javaClass.getMethod(it.methodName, HttpServletRequest::class.java, HttpServletResponse::class.java, restControllerBean.classOfEntity.java))
            } else if (it == GET_BY_ID || it == DELETE_BY_ID) {
                handlerMapping.registerMapping(
                        RequestMappingInfo.paths("${uriValue}${it.uri}")
                                .methods(it.httpMethod)
                                .produces(MediaType.APPLICATION_JSON_VALUE)
                                .build(),
                        proxyRestController,
                        proxyRestController.javaClass.getMethod(it.methodName, HttpServletRequest::class.java, HttpServletResponse::class.java, restControllerBean.classOfId.java))
            }
        }
    }
}