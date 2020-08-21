package vip.yazilim.libs.springvip.rest

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import vip.yazilim.libs.springvip.bean.IRestResponseBuilder
import vip.yazilim.libs.springvip.service.IGenericServiceCrud
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
abstract class AGenericRestCru<E : Any, ID : Any>(restResponseBuilder: IRestResponseBuilder, genericServiceCrud: IGenericServiceCrud<E, ID>, classOfEntity: KClass<E>) : AGenericRestRead<E, ID>(restResponseBuilder, genericServiceCrud, classOfEntity) {

    // (C) create Operations
    @PostMapping("/")
    override fun restCreate(request: HttpServletRequest, response: HttpServletResponse, @Valid @RequestBody entity: E): Any {
        return super.restCreate(request, response, entity)
    }

    @PutMapping("/")
    override fun restUpdate(request: HttpServletRequest, response: HttpServletResponse, @Valid @RequestBody entity: E): Any {
        return super.restUpdate(request, response, entity)
    }
}