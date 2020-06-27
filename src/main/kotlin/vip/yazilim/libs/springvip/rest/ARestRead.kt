package vip.yazilim.libs.springvip.rest

/**
 * @author Emre Sen, 24.06.2020
 * @contact yazilim.vip
 */


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import vip.yazilim.libs.springvip.config.SpringVipConfiguration
import vip.yazilim.libs.springvip.service.ICrudService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass

/**
 * Generic REST Controller Implementations for generic Read operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
abstract class ARestRead<E : Any, ID>(
        private val springVipConfiguration: SpringVipConfiguration
) {

    protected abstract val crudService: ICrudService<E, ID>
    protected abstract val classOfEntity: KClass<E>

    // (R) read Operations
    @GetMapping("/")
    fun getAll(request: HttpServletRequest, response: HttpServletResponse): Any {
        return springVipConfiguration.generateResponse(responseBody = crudService.getAll()
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response);
    }

    @GetMapping("/{id}")
    fun getById(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): Any {

        // get entity
        val entity = crudService.getById(id)
        require(entity.isPresent) {
            throw NoSuchElementException("${classOfEntity.simpleName} Not Found :: ${id.toString()}")
        }

        // init response
        return springVipConfiguration.generateResponse(responseBody = entity.get()
                , httpStatus = HttpStatus.OK
                , request = request
                , response = response)
    }
}