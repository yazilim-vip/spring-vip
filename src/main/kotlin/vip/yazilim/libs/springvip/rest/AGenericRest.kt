package vip.yazilim.libs.springvip.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import vip.yazilim.libs.springvip.config.ARestConfig
import vip.yazilim.libs.springvip.rest.model.RestResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AGenericRest<E : Any, ID>(
        private val restConfig: ARestConfig<E, ID>
) {

    fun <E : Any, ID> restGetAll(restConfig: ARestConfig<E, ID>
                                 , request: HttpServletRequest
                                 , response: HttpServletResponse): Any {
        return restConfig.restResponseGenerator.generateResponse(responseBody = restConfig.crudService.getAll()
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }

    fun <E : Any, ID> restGetById(restConfig: ARestConfig<E, ID>
                                  , request: HttpServletRequest
                                  , response: HttpServletResponse
                                  , @PathVariable id: ID): Any {

        // get entity
        val entity = restConfig.crudService.getById(id)
        require(entity.isPresent) {
            throw NoSuchElementException("${restConfig.classOfEntity.simpleName} Not Found :: ${id.toString()}")
        }

        // init response
        return restConfig.restResponseGenerator.generateResponse(responseBody = entity.get()
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }


    fun <E : Any, ID> restCreate(restConfig: ARestConfig<E, ID>
                                 , request: HttpServletRequest
                                 , response: HttpServletResponse
                                 , entity: E): Any {

        // create entity
        val createdEntity = restConfig.crudService.create(entity)

        // init response
        return restConfig.restResponseGenerator.generateResponse(responseBody = createdEntity
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }


    fun <E : Any, ID> restUpdate(restConfig: ARestConfig<E, ID>
                                 , request: HttpServletRequest
                                 , response: HttpServletResponse
                                 , entity: E): Any {

        // update entity
        val updatedEntity = restConfig.crudService.update(entity)

        // init response
        return restConfig.restResponseGenerator.generateResponse(responseBody = updatedEntity
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }

    // (D) delete Operations
    fun <E : Any, ID> restDelete(restConfig: ARestConfig<E, ID>
                                 , request: HttpServletRequest
                                 , response: HttpServletResponse
                                 , @PathVariable id: ID): Any {

        // init response
        return restConfig.restResponseGenerator.generateResponse(responseBody = restConfig.crudService.deleteById(id)
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response);
    }

}