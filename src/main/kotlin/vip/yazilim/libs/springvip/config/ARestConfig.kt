package vip.yazilim.libs.springvip.config

import org.springframework.http.HttpStatus
import org.springframework.web.servlet.HandlerMapping
import vip.yazilim.libs.springvip.rest.model.RestResponse
import vip.yazilim.libs.springvip.service.ICrudService
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass

abstract class ARestConfig<E : Any, ID : Any>(val springVipConfig: SpringVipConfig
                                              , val crudService: ICrudService<E, ID>
                                              , val classOfEntity: KClass<E>
) {

    abstract fun generateResponse(responseBody: Any
                                  , httpStatus: HttpStatus
                                  , request: HttpServletRequest
                                  , response: HttpServletResponse): Any

}


open class ARestDefaultConfig<E : Any, ID : Any>(springVipConfig: SpringVipConfig
                                                 , crudService: ICrudService<E, ID>
                                                 , classOfEntity: KClass<E>)
    : ARestConfig<E, ID>(springVipConfig, crudService, classOfEntity) {

    override fun generateResponse(responseBody: Any, httpStatus: HttpStatus, request: HttpServletRequest, response: HttpServletResponse): RestResponse<Any> {
        val path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) as String
        val restResponse: RestResponse<Any> = RestResponse(
                timestamp = Date().time,
                path = path,
                message = httpStatus.reasonPhrase,
                data = responseBody
        )
        response.setIntHeader("status", httpStatus.value())
        return restResponse
    }
}
