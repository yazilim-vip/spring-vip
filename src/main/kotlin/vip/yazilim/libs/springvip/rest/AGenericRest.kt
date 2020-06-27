package vip.yazilim.libs.springvip.rest


import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import vip.yazilim.libs.springvip.config.ARestConfig
import vip.yazilim.libs.springvip.rest.model.RestResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

open class AGenericRest<E : Any, ID : Any> (
        private val restConfig: ARestConfig<E, ID>
) {


    fun restGetAll(request: HttpServletRequest
                   , response: HttpServletResponse): Any {
        return restConfig.generateResponse(responseBody = restConfig.crudService.getAll()
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }

    fun restGetById(request: HttpServletRequest
                    , response: HttpServletResponse
                    , @PathVariable id: ID): Any {

        // get entity
        val entity = restConfig.crudService.getById(id)
        require(entity.isPresent) {
            throw NoSuchElementException("${restConfig.classOfEntity.simpleName} Not Found :: ${id.toString()}")
        }

        // init response
        return restConfig.generateResponse(responseBody = entity.get()
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }


    fun restCreate(request: HttpServletRequest
                   , response: HttpServletResponse
                   , entity: E): Any {

        // create entity
        val createdEntity = restConfig.crudService.create(entity)

        // init response
        return restConfig.generateResponse(responseBody = createdEntity
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }


    fun restUpdate(request: HttpServletRequest
                   , response: HttpServletResponse
                   , entity: E): Any {

        // update entity
        val updatedEntity = restConfig.crudService.update(entity)

        // init response
        return restConfig.generateResponse(responseBody = updatedEntity
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }

    // (D) delete Operations
    fun restDelete(request: HttpServletRequest
                   , response: HttpServletResponse
                   , @PathVariable id: ID): Any {

        // init response
        return restConfig.generateResponse(responseBody = restConfig.crudService.deleteById(id)
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response);
    }
}