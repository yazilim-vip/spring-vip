package vip.yazilim.libs.springvip.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import vip.yazilim.libs.springvip.rest.model.RestResponse
import vip.yazilim.libs.springvip.service.ICrudService
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Generic REST Controller Implementations for generic Read operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
abstract class ARestRead<E, ID> {
    protected abstract val service: ICrudService<E, ID>
    protected abstract val classOfEntity: Class<E>

    // (R) read Operations
    @GetMapping("/")
    fun getAll(request: HttpServletRequest, response: HttpServletResponse): RestResponse<List<E>> {

        // get repo
        val crudService = service

        // get entity list
        var entityList: List<E?>? = null
        entityList = crudService.all

        // init response
        return RestResponse.Companion.generateResponse(entityList, HttpStatus.OK, request, response)
    }

    @GetMapping("/{id}")
    fun getById(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): RestResponse<E> {

        // get repo
        val crudService = service

        // get entity
        val entity = crudService.getById(id)
        if (!entity!!.isPresent) {
            val className = classOfEntity.simpleName
            throw NoSuchElementException(className + " Not Found :: " + id.toString())
        }

        // init response
        return RestResponse.Companion.generateResponse(entity.get(), HttpStatus.OK, request, response)
    }
}