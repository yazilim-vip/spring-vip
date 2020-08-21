package vip.yazilim.libs.springvip.rest

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import vip.yazilim.libs.springvip.bean.ISpringVipHttpRestConfig
import vip.yazilim.libs.springvip.service.ICrudService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass


/**
 * Generic REST Controller Implementations for generic CRUD operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
abstract class AGenericRestCrud<E : Any, ID : Any>(springVipHttpRestConfig: ISpringVipHttpRestConfig, crudService: ICrudService<E, ID>, classOfEntity: KClass<E>) : AGenericRestCru<E, ID>(springVipHttpRestConfig, crudService, classOfEntity) {

    // (D) delete Operations
    @DeleteMapping("/{id}")
    override fun restDelete(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): Any {
        return super.restDelete(request, response, id)
    }

}