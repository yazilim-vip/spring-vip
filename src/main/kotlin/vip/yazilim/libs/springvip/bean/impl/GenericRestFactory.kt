package vip.yazilim.libs.springvip.bean.impl

import net.bytebuddy.ByteBuddy
import net.bytebuddy.description.annotation.AnnotationDescription
import net.bytebuddy.dynamic.DynamicType
import net.bytebuddy.implementation.MethodDelegation
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import vip.yazilim.libs.springvip.bean.IGenericRestFactory
import vip.yazilim.libs.springvip.util.generic.rest.GenericCrudMethods.*
import vip.yazilim.libs.springvip.util.generic.rest.VipGenericRest
import java.lang.reflect.Modifier
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Default  RestResponse Builder Implementation
 */
@Component
class GenericRestFactory(
        private val handlerMapping: RequestMappingHandlerMapping,
) : IGenericRestFactory {
    override fun buildProxyRestController(restControllerBean: Any, vipGenericRest: VipGenericRest): Any {
        /*
        -------------------------------
        | DEFINE NEW CONTROLLER CLASS
        -------------------------------
         */
        var builder: DynamicType.Builder<Any?> = ByteBuddy()
                .subclass(Any::class.java)
                .name("${restControllerBean::class.simpleName}GenericController")
                .annotateType(AnnotationDescription.Builder
                        .ofType(RestController::class.java) // don't use `request` mapping here
                        .build()
                )

        /*
        -------------------------------
        | BEGIN DEFINE CUSTOM METHODS
        -------------------------------
         */
        vipGenericRest.methods.forEach {
            when (it) {
//                CREATE -> println("here")
//                DELETE_ALL -> println("here")
//                DELETE_BY_ID -> println("here")
//                DELETE -> println("here")
                GET_ALL -> {
                    builder = builder.defineMethod(it.methodName, Any::class.java, Modifier.PUBLIC)
                            .withParameters(HttpServletRequest::class.java, HttpServletResponse::class.java)
                            .intercept(MethodDelegation.to(restControllerBean))
                }
//                GET_BY_ID -> println("here")
//                SAVE -> println("here")
//                UPDATE -> println("here")
            }
        }

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
        return builder.make()
                .load(restControllerBean.javaClass.classLoader)
                .loaded
                .newInstance()!!
    }
}