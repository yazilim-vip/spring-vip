package vip.yazilim.libs.springvip.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import vip.yazilim.libs.springvip.rest.model.RestResponse
import vip.yazilim.libs.springvip.rest.model.generateResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

/**
 * Generic REST Controller Implementations for generic Create, Read and Update
 * operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
abstract class ARestCru<E : Any, ID> : ARestRead<E, ID>() {

    // (C) create Operations
    @PostMapping("/")
    open fun create(request: HttpServletRequest, response: HttpServletResponse, @Valid @RequestBody entity: E): RestResponse<E> {


        // get repo
        val crudService = service

        // create entity
        val createdEntity = crudService.create(entity)

        // init response
        return generateResponse(createdEntity, HttpStatus.CREATED, request, response)
    }

    // (U) update Operations
    @PutMapping("/")
    open fun update(request: HttpServletRequest, response: HttpServletResponse, @Valid @RequestBody entity: E): RestResponse<E> {

        // get repo
        val crudService = service

        // update entity
        val updatedEntity = crudService.update(entity)

        // init response
        return generateResponse(updatedEntity, HttpStatus.OK, request, response)
    }


}