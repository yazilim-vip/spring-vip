package vip.yazilim.libs.springvip.bean.impl

import net.bytebuddy.ByteBuddy
import net.bytebuddy.description.annotation.AnnotationDescription
import net.bytebuddy.dynamic.DynamicType
import net.bytebuddy.implementation.MethodDelegation
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import vip.yazilim.libs.springvip.bean.IGenericRestFactory
import vip.yazilim.libs.springvip.util.generic.rest.AGenericRest
import vip.yazilim.libs.springvip.util.generic.rest.GenericCrudMethods
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

    override fun <E, ID, T : AGenericRest<E, ID>> buildProxyRestController(restControllerBean: T, vipGenericRest: VipGenericRest): Any {

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
            builder = when (it) {
                GET_ALL -> builder.defineMethod(GET_ALL.methodName, Any::class.java, Modifier.PUBLIC)
                        .withParameter(HttpServletRequest::class.java)
                        .withParameter(HttpServletResponse::class.java)
                        .intercept(MethodDelegation.to(restControllerBean))
                GET_BY_ID -> builder.defineMethod(GET_BY_ID.methodName, Any::class.java, Modifier.PUBLIC)
                        .withParameter(HttpServletRequest::class.java)
                        .withParameter(HttpServletResponse::class.java)
                        .withParameter(restControllerBean.classOfId.java)
                        .annotateParameter(AnnotationDescription.Builder
                                .ofType(PathVariable::class.java)
                                .define("name", "id")
                                .build())
                        .intercept(MethodDelegation.to(restControllerBean))
                CREATE -> TODO()
                DELETE_ALL -> TODO()
                DELETE_BY_ID -> TODO()
                DELETE -> TODO()
                SAVE -> TODO()
                UPDATE -> TODO()
            }
        }

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