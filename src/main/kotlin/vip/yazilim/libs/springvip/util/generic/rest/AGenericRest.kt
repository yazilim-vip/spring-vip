package vip.yazilim.libs.springvip.util.generic.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import vip.yazilim.libs.springvip.bean.IRestResponseBuilder
import vip.yazilim.libs.springvip.util.generic.service.IGenericServiceCrud
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass

/**.
 * Generic Implementation of a REST controller
 */
abstract class AGenericRest<E : Any, ID : Any>(
        private val restResponseBuilder: IRestResponseBuilder,
        private val genericServiceCrud: IGenericServiceCrud<E, ID>,
        val classOfEntity: KClass<E>,
        val classOfId: KClass<ID>,
) {
    constructor(
            restResponseBuilder: IRestResponseBuilder,
            genericServiceCrud: IGenericServiceCrud<E, ID>,
            classOfEntity: Class<E>,
            classOfId: Class<ID>,
    ) : this(restResponseBuilder, genericServiceCrud, classOfEntity.kotlin, classOfId.kotlin)


    open fun getAllGenericImpl(request: HttpServletRequest, response: HttpServletResponse): Any {
        return restResponseBuilder.generateRestResponse(responseBody = genericServiceCrud.getAll(), httpStatus = HttpStatus.OK, request = request, response = response)
    }

    open fun getByIdGenericImpl(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): Any {

        // get entity
        val entity = genericServiceCrud.getById(id)
        require(entity.isPresent) {
            throw NoSuchElementException("${classOfEntity.simpleName} Not Found::${id.toString()}")
        }

        // init response
        return restResponseBuilder.generateRestResponse(responseBody = entity.get(), httpStatus = HttpStatus.OK, request = request, response = response)
    }

    open fun saveGenericImpl(request: HttpServletRequest, response: HttpServletResponse, entity: E): Any {

        // create entity
        val savedEntity = genericServiceCrud.save(entity)

        // init response
        return restResponseBuilder.generateRestResponse(responseBody = savedEntity, httpStatus = HttpStatus.OK, request = request, response = response)
    }

    open fun createGenericImpl(request: HttpServletRequest, response: HttpServletResponse, entity: E): Any {

        // create entity
        val createdEntity = genericServiceCrud.create(entity)

        // init response
        return restResponseBuilder.generateRestResponse(responseBody = createdEntity, httpStatus = HttpStatus.OK, request = request, response = response)
    }


    open fun updateGenericImpl(request: HttpServletRequest, response: HttpServletResponse, entity: E): Any {

        // update entity
        val updatedEntity = genericServiceCrud.update(entity)

        // init response
        return restResponseBuilder.generateRestResponse(responseBody = updatedEntity, httpStatus = HttpStatus.OK, request = request, response = response)
    }

    // (D) delete Operations
    open fun deleteByIdGenericImpl(request: HttpServletRequest, response: HttpServletResponse, id: ID): Any {

        // init response
        return restResponseBuilder.generateRestResponse(responseBody = genericServiceCrud.deleteById(id), httpStatus = HttpStatus.OK, request = request, response = response);
    }

    open fun deleteGenericImpl(request: HttpServletRequest, response: HttpServletResponse, entity: E): Any {

        // init response
        return restResponseBuilder.generateRestResponse(responseBody = genericServiceCrud.delete(entity), httpStatus = HttpStatus.OK, request = request, response = response);
    }

    open fun deleteAllGenericImpl(request: HttpServletRequest, response: HttpServletResponse
    ): Any {
        // init response
        return restResponseBuilder.generateRestResponse(responseBody = genericServiceCrud.deleteAll(), httpStatus = HttpStatus.OK, request = request, response = response);
    }
}