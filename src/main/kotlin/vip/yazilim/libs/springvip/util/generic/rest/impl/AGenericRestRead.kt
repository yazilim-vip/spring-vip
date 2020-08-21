package vip.yazilim.libs.springvip.util.generic.rest.impl

/**
 * @author Emre Sen, 24.06.2020
 * @contact yazilim.vip
 */


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import vip.yazilim.libs.springvip.bean.IRestResponseBuilder
import vip.yazilim.libs.springvip.util.generic.rest.IGenericRestRead
import vip.yazilim.libs.springvip.util.generic.service.IGenericServiceCrud
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass

/**
 * Generic REST Controller Implementations for generic Read operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
abstract class AGenericRestRead<E : Any, ID : Any>(restResponseBuilder: IRestResponseBuilder, genericServiceCrud: IGenericServiceCrud<E, ID>, classOfEntity: KClass<E>)
    : AGenericRest<E, ID>(restResponseBuilder, genericServiceCrud, classOfEntity), IGenericRestRead<E, ID> {

    // (R) read Operations
    @GetMapping("/")
    override fun getAll(request: HttpServletRequest, response: HttpServletResponse): Any {
        return super.getAllGenericImpl(request, response)
    }

    @GetMapping("/{id}")
    override fun getById(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): Any {
        return super.getByIdGenericImpl(request, response, id)
    }
}