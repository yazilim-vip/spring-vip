package vip.yazilim.libs.springvip.bean.impl

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import vip.yazilim.libs.springvip.bean.IGenericRestControllerRegisterer
import vip.yazilim.libs.springvip.util.generic.rest.GenericCrudMethods
import vip.yazilim.libs.springvip.util.generic.rest.VipGenericRest
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

/**
 * Default  RestResponse Builder Implementation
 */
@Component
class GenericRestControllerRegisterer(
        private val handlerMapping: RequestMappingHandlerMapping,
) : IGenericRestControllerRegisterer {

    override fun registerMappings(restControllerBeanClass: KClass<*>, proxyRestController: Any, vipGenericRest: VipGenericRest) {
        val uriValues = restControllerBeanClass.findAnnotation<RequestMapping>()?.value ?: emptyArray()
        uriValues.forEach { uriValue: String ->
            run {
                vipGenericRest.methods.forEach {
                    when (it) {
//                        GenericCrudMethods.CREATE -> println("here")
//                        GenericCrudMethods.DELETE_ALL -> println("here")
//                        GenericCrudMethods.DELETE_BY_ID -> println("here")
//                        GenericCrudMethods.DELETE -> println("here")
                        GenericCrudMethods.GET_ALL -> {
                            handlerMapping.registerMapping(
                                    RequestMappingInfo.paths("${uriValue}${it.uri}")
                                            .methods(it.httpMethod)
                                            .produces(MediaType.APPLICATION_JSON_VALUE)
                                            .build(),
                                    proxyRestController,
                                    proxyRestController.javaClass.getMethod(it.methodName, HttpServletRequest::class.java, HttpServletResponse::class.java))
                        }
//                        GenericCrudMethods.GET_BY_ID -> println("here")
//                        GenericCrudMethods.SAVE -> println("here")
//                        GenericCrudMethods.UPDATE -> println("here")
                    }
                }
            }

        }
    }
}