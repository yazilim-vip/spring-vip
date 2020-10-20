package vip.yazilim.libs.springvip.bean.impl

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.core.annotation.AnnotatedElementUtils
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import vip.yazilim.libs.springvip.bean.IGenericRestRegisterer
import vip.yazilim.libs.springvip.util.generic.rest.AGenericRest
import vip.yazilim.libs.springvip.util.generic.rest.method.GenericMethod
import vip.yazilim.libs.springvip.util.generic.rest.GenericMethodType
import vip.yazilim.libs.springvip.util.generic.rest.GenericMethodType.*
import vip.yazilim.libs.springvip.util.generic.rest.GenericRest
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Default  RestResponse Builder Implementation
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class GenericRestRegisterer(
        private val handlerMapping: RequestMappingHandlerMapping,
) : IGenericRestRegisterer {

    override fun <E, ID, T : AGenericRest<E, ID>> registerMappings(restControllerBean: T, genericRest: GenericRest, proxyRestController: Any, uriValue: String) {
        AnnotatedElementUtils.findAllMergedAnnotations(restControllerBean::class.java, GenericMethod::class.java).forEach {
            val genericMethodType = it.genericMethodType
            val methodUri = it.uri
            if (genericMethodType == GET_ALL || genericMethodType == DELETE_ALL) {
                handlerMapping.registerMapping(
                        RequestMappingInfo.paths("${uriValue}${methodUri}")
                                .methods(genericMethodType.httpMethod)
                                .produces(MediaType.APPLICATION_JSON_VALUE)
                                .build(),
                        proxyRestController,
                        proxyRestController.javaClass.getMethod(genericMethodType.methodName, HttpServletRequest::class.java, HttpServletResponse::class.java))
            } else if (genericMethodType == CREATE || genericMethodType == UPDATE || genericMethodType == SAVE || genericMethodType == DELETE) {
                handlerMapping.registerMapping(
                        RequestMappingInfo.paths("${uriValue}${methodUri}")
                                .methods(genericMethodType.httpMethod)
                                .produces(MediaType.APPLICATION_JSON_VALUE)
                                .build(),
                        proxyRestController,
                        proxyRestController.javaClass.getMethod(genericMethodType.methodName, HttpServletRequest::class.java, HttpServletResponse::class.java, restControllerBean.classOfEntity.java))
            } else if (genericMethodType == GET_BY_ID || genericMethodType == DELETE_BY_ID) {
                handlerMapping.registerMapping(
                        RequestMappingInfo.paths("${uriValue}${methodUri}")
                                .methods(genericMethodType.httpMethod)
                                .produces(MediaType.APPLICATION_JSON_VALUE)
                                .build(),
                        proxyRestController,
                        proxyRestController.javaClass.getMethod(genericMethodType.methodName, HttpServletRequest::class.java, HttpServletResponse::class.java, restControllerBean.classOfId.java))
            }
        }
    }
}