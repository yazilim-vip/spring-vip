package vip.yazilim.libs.springvip.util.generic.rest.impl


import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import vip.yazilim.libs.springvip.bean.IRestResponseBuilder
import vip.yazilim.libs.springvip.util.generic.service.IGenericServiceCrud
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass

abstract class AGenericRest<E : Any, ID : Any>(
        private val restResponseBuilder: IRestResponseBuilder,
        private val genericServiceCrud: IGenericServiceCrud<E, ID>,
        private val classOfEntity: KClass<E>
) {


    open fun getAllGenericImpl(request: HttpServletRequest
                               , response: HttpServletResponse): Any {
        return restResponseBuilder.generateRestResponse(responseBody = genericServiceCrud.getAll()
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }

    open fun getByIdGenericImpl(request: HttpServletRequest
                                , response: HttpServletResponse
                                , @PathVariable id: ID): Any {

        // get entity
        val entity = genericServiceCrud.getById(id)
        require(entity.isPresent) {
            throw NoSuchElementException("${classOfEntity.simpleName} Not Found :: ${id.toString()}")
        }

        // init response
        return restResponseBuilder.generateRestResponse(responseBody = entity.get()
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }


    open fun createGenericImpl(request: HttpServletRequest
                               , response: HttpServletResponse
                               , entity: E): Any {

        // create entity
        val createdEntity = genericServiceCrud.create(entity)

        // init response
        return restResponseBuilder.generateRestResponse(responseBody = createdEntity
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }


    open fun updateGenericImpl(request: HttpServletRequest
                               , response: HttpServletResponse
                               , entity: E): Any {

        // update entity
        val updatedEntity = genericServiceCrud.update(entity)

        // init response
        return restResponseBuilder.generateRestResponse(responseBody = updatedEntity
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }

    // (D) delete Operations
    open fun deleteGenericImpl(request: HttpServletRequest
                               , response: HttpServletResponse
                               , @PathVariable id: ID): Any {

        // init response
        return restResponseBuilder.generateRestResponse(responseBody = genericServiceCrud.deleteById(id)
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response);
    }
}