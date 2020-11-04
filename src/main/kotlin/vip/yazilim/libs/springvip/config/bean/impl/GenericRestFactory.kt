package vip.yazilim.libs.springvip.config.bean.impl

import net.bytebuddy.ByteBuddy
import net.bytebuddy.description.annotation.AnnotationDescription
import net.bytebuddy.dynamic.DynamicType
import net.bytebuddy.implementation.MethodDelegation
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.core.annotation.AnnotatedElementUtils
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import vip.yazilim.libs.springvip.config.bean.IGenericRestFactory
import vip.yazilim.libs.springvip.util.generic.rest.AGenericRest
import vip.yazilim.libs.springvip.util.generic.rest.AutoGeneratedRest
import vip.yazilim.libs.springvip.util.generic.rest.method.GenericMethod
import vip.yazilim.libs.springvip.util.generic.rest.GenericMethodType.*
import vip.yazilim.libs.springvip.util.generic.rest.GenericRest
import java.lang.reflect.Modifier
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Default  RestResponse Builder Implementation
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class GenericRestFactory(
        private val handlerMapping: RequestMappingHandlerMapping,
) : IGenericRestFactory {

    override fun <E, ID, T : AGenericRest<E, ID>> buildProxyRestController(restControllerBean: T, genericRest: GenericRest, uri: String): Any {
        /*
         -------------------------------
        | DEFINE NEW CONTROLLER CLASS
        -------------------------------
         */
        val className = if (genericRest.name.isBlank()) {
            "${restControllerBean::class.simpleName}Crud${uri.replace("/", "$")}"
        } else {
            genericRest.name;
        }

        var builder: DynamicType.Builder<Any?> = ByteBuddy()
                .subclass(Any::class.java)
                .name(className)
                .annotateType(AnnotationDescription.Builder
                        .ofType(RestController::class.java) // don't use `request` mapping here
                        .build()
                )
                .annotateType(AnnotationDescription.Builder
                        .ofType(AutoGeneratedRest::class.java) // don't use `request` mapping here
                        .build()
                )


        /*
        -------------------------------
        | BEGIN DEFINE CUSTOM METHODS
        -------------------------------
         */

        AnnotatedElementUtils.findAllMergedAnnotations(restControllerBean::class.java, GenericMethod::class.java).forEach {
            val genericMethodType = it.genericMethodType
            val methodBuilder = builder.defineMethod(genericMethodType.methodName, Any::class.java, Modifier.PUBLIC);
            if (genericMethodType == GET_ALL || genericMethodType == DELETE_ALL) {
                builder = methodBuilder.withParameter(HttpServletRequest::class.java)
                        .withParameter(HttpServletResponse::class.java)
                        .intercept(MethodDelegation.to(restControllerBean))
            } else if (genericMethodType == CREATE || genericMethodType == UPDATE || genericMethodType == SAVE || genericMethodType == DELETE) {
                builder = methodBuilder
                        .withParameter(HttpServletRequest::class.java)
                        .withParameter(HttpServletResponse::class.java)
                        .withParameter(restControllerBean.classOfEntity.java)
                        .annotateParameter(AnnotationDescription.Builder
                                .ofType(RequestBody::class.java)
                                .build())
                        .intercept(MethodDelegation.to(restControllerBean))
            } else if (genericMethodType == GET_BY_ID || genericMethodType == DELETE_BY_ID) {
                builder = methodBuilder
                        .withParameter(HttpServletRequest::class.java)
                        .withParameter(HttpServletResponse::class.java)
                        .withParameter(restControllerBean.classOfId.java)
                        .annotateParameter(AnnotationDescription.Builder
                                .ofType(PathVariable::class.java)
                                .define("name", "id")
                                .build())
                        .intercept(MethodDelegation.to(restControllerBean))

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