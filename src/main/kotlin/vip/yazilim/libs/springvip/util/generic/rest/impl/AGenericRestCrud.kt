package vip.yazilim.libs.springvip.util.generic.rest.impl

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import vip.yazilim.libs.springvip.bean.IRestResponseBuilder
import vip.yazilim.libs.springvip.util.generic.rest.IGenericRestCrud
import vip.yazilim.libs.springvip.util.generic.service.IGenericServiceCrud
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass


/**
 * Generic REST Controller Implementations for generic CRUD operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
abstract class AGenericRestCrud<E : Any, ID : Any>(
        restResponseBuilder: IRestResponseBuilder, genericServiceCrud: IGenericServiceCrud<E, ID>, classOfEntity: KClass<E>
) : AGenericRestWrite<E, ID>(restResponseBuilder, genericServiceCrud, classOfEntity), IGenericRestCrud<E, ID> {

    // (D) delete Operations
    @DeleteMapping("/{id}")
    override fun restDelete(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): Any {
        return super.restDelete(request, response, id)
    }

}