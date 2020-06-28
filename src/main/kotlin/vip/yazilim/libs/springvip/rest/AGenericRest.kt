package vip.yazilim.libs.springvip.rest


import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import vip.yazilim.libs.springvip.config.SpringVipConfig
import vip.yazilim.libs.springvip.service.ICrudService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass

abstract class AGenericRest<E : Any, ID : Any>(
        protected val springVipConfig: SpringVipConfig,
        protected val crudService: ICrudService<E, ID>,
        protected val classOfEntity: KClass<E>
) {


    open fun restGetAll(request: HttpServletRequest
                        , response: HttpServletResponse): Any {
        return springVipConfig.generateRestResponse(responseBody = crudService.getAll()
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }

    open fun restGetById(request: HttpServletRequest
                         , response: HttpServletResponse
                         , @PathVariable id: ID): Any {

        // get entity
        val entity = crudService.getById(id)
        require(entity.isPresent) {
            throw NoSuchElementException("${classOfEntity.simpleName} Not Found :: ${id.toString()}")
        }

        // init response
        return springVipConfig.generateRestResponse(responseBody = entity.get()
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }


    open fun restCreate(request: HttpServletRequest
                        , response: HttpServletResponse
                        , entity: E): Any {

        // create entity
        val createdEntity = crudService.create(entity)

        // init response
        return springVipConfig.generateRestResponse(responseBody = createdEntity
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }


    open fun restUpdate(request: HttpServletRequest
                        , response: HttpServletResponse
                        , entity: E): Any {

        // update entity
        val updatedEntity = crudService.update(entity)

        // init response
        return springVipConfig.generateRestResponse(responseBody = updatedEntity
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }

    // (D) delete Operations
    open fun restDelete(request: HttpServletRequest
                        , response: HttpServletResponse
                        , @PathVariable id: ID): Any {

        // init response
        return springVipConfig.generateRestResponse(responseBody = crudService.deleteById(id)
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response);
    }
}