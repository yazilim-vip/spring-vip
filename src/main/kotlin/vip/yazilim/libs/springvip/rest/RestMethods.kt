package vip.yazilim.libs.springvip.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import vip.yazilim.libs.springvip.config.SpringVipConfiguration
import vip.yazilim.libs.springvip.service.ICrudService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid
import kotlin.reflect.KClass


fun <E : Any, ID> restGetAll(springVipConfiguration: SpringVipConfiguration
                             , restConfig: ARestConfig<E, ID>
                             , request: HttpServletRequest
                             , response: HttpServletResponse): Any {
    return springVipConfiguration.generateResponse(responseBody = restConfig.crudService.getAll()
            , httpStatus = HttpStatus.OK
            , request = request
            , response = response)
}

fun <E : Any, ID> restGetById(springVipConfiguration: SpringVipConfiguration
                              , restConfig: ARestConfig<E, ID>
                              , request: HttpServletRequest
                              , response: HttpServletResponse
                              , @PathVariable id: ID): Any {

    // get entity
    val entity = restConfig.crudService.getById(id)
    require(entity.isPresent) {
        throw NoSuchElementException("${restConfig.classOfEntity.simpleName} Not Found :: ${id.toString()}")
    }

    // init response
    return springVipConfiguration.generateResponse(responseBody = entity.get()
            , httpStatus = HttpStatus.OK
            , request = request
            , response = response)
}


fun <E : Any, ID> restCreate(springVipConfiguration: SpringVipConfiguration
                             , restConfig: ARestConfig<E, ID>
                             , request: HttpServletRequest
                             , response: HttpServletResponse
                             , entity: E): Any {

    // create entity
    val createdEntity = restConfig.crudService.create(entity)

    // init response
    return springVipConfiguration.generateResponse(responseBody = createdEntity
            , httpStatus = HttpStatus.OK
            , request = request
            , response = response)
}


fun <E : Any, ID> restUpdate(springVipConfiguration: SpringVipConfiguration
                             , restConfig: ARestConfig<E, ID>
                             , request: HttpServletRequest
                             , response: HttpServletResponse
                             , entity: E): Any {

    // update entity
    val updatedEntity = restConfig.crudService.update(entity)

    // init response
    return springVipConfiguration.generateResponse(responseBody = updatedEntity
            , httpStatus = HttpStatus.OK
            , request = request
            , response = response)
}

// (D) delete Operations
@DeleteMapping("/{id}")
fun <E : Any, ID> restDelete(springVipConfiguration: SpringVipConfiguration
                             , restConfig: ARestConfig<E, ID>
                             , request: HttpServletRequest
                             , response: HttpServletResponse
                             , @PathVariable id: ID): Any {

    // init response
    return springVipConfiguration.generateResponse(responseBody = restConfig.crudService.deleteById(id)
            , httpStatus = HttpStatus.OK
            , request = request
            , response = response);
}