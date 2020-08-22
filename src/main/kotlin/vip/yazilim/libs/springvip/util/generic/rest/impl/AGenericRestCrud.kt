package vip.yazilim.libs.springvip.util.generic.rest.impl

import vip.yazilim.libs.springvip.bean.IRestResponseBuilder
import vip.yazilim.libs.springvip.util.generic.rest.AGenericRest
import vip.yazilim.libs.springvip.util.generic.rest.IGenericRestCrud
import vip.yazilim.libs.springvip.util.generic.service.IGenericServiceCrud
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass

/**
 *
 * @author maemresen - <maemresen@yazilim.vip>
 * 22.08.2020
 */
open class AGenericRestCrud<E : Any, ID : Any>(restResponseBuilder: IRestResponseBuilder, genericServiceCrud: IGenericServiceCrud<E, ID>, classOfEntity: KClass<E>)
    : AGenericRest<E, ID>(restResponseBuilder, genericServiceCrud, classOfEntity), IGenericRestCrud<E, ID> {

    override fun getAll(request: HttpServletRequest, response: HttpServletResponse): Any {
        return super.getAllGenericImpl(request, response)
    }

    override fun getById(request: HttpServletRequest, response: HttpServletResponse, id: ID): Any {
        return super.getByIdGenericImpl(request, response, id)
    }

    override fun create(request: HttpServletRequest, response: HttpServletResponse, entity: E): Any {
        return super.createGenericImpl(request, response, entity)
    }

    override fun update(request: HttpServletRequest, response: HttpServletResponse, newEntity: E): Any {
        return super.updateGenericImpl(request, response, newEntity)
    }

    override fun deleteById(request: HttpServletRequest, response: HttpServletResponse, id: ID): Any {
        return super.deleteByIdGenericImpl(request,response, id)
    }
}