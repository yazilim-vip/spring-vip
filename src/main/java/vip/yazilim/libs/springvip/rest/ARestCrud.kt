package vip.yazilim.libs.springvip.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import vip.yazilim.libs.springvip.rest.model.RestResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Generic REST Controller Implementations for generic CRUD operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
abstract class ARestCrud<E, ID> : ARestCru<E, ID>() {
    // (D) delete Operations
    @DeleteMapping("/{id}")
    fun delete(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): RestResponse<Boolean> {

        // get repo
        val crudService = service

        // delete status
        val status: Boolean

        // delete entity
        status = crudService!!.deleteById(id)

        // init response
        return RestResponse.Companion.generateResponse(status, HttpStatus.OK, request, response)
    }
}