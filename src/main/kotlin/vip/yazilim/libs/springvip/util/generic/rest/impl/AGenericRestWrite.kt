package vip.yazilim.libs.springvip.util.generic.rest.impl

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import vip.yazilim.libs.springvip.bean.IRestResponseBuilder
import vip.yazilim.libs.springvip.util.generic.rest.IGenericRestWrite
import vip.yazilim.libs.springvip.util.generic.service.IGenericServiceCrud
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid
import kotlin.reflect.KClass

/**
 * Generic REST Controller Implementations for generic Create, Read and Update
 * operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
abstract class AGenericRestWrite<E : Any, ID : Any>(restResponseBuilder: IRestResponseBuilder, genericServiceCrud: IGenericServiceCrud<E, ID>, classOfEntity: KClass<E>)
    : AGenericRestRead<E, ID>(restResponseBuilder, genericServiceCrud, classOfEntity), IGenericRestWrite<E, ID> {

    // (C) create Operations
    @PostMapping("/")
    override fun createGenericImpl(request: HttpServletRequest, response: HttpServletResponse, @Valid @RequestBody entity: E): Any {
        return super.createGenericImpl(request, response, entity)
    }

    @PutMapping("/")
    override fun updateGenericImpl(request: HttpServletRequest, response: HttpServletResponse, @Valid @RequestBody entity: E): Any {
        return super.updateGenericImpl(request, response, entity)
    }
}