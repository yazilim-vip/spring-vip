package vip.yazilim.libs.springvip.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import vip.yazilim.libs.springvip.config.SpringVipConfiguration
import vip.yazilim.libs.springvip.service.ICrudService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid
import kotlin.reflect.KClass


abstract class ARestConfig<E : Any, ID>(
        private val springVipConfiguration: SpringVipConfiguration
) {
    abstract val crudService: ICrudService<E, ID>
    abstract val classOfEntity: KClass<E>
}

abstract class ARestGetAll<E : Any, ID>(
        private val springVipConfiguration: SpringVipConfiguration,
        private val restConfig: ARestConfig<E, ID>
) {

    // (R) read Operations
    @GetMapping("/")
    fun getAll(request: HttpServletRequest, response: HttpServletResponse): Any {
        return restGetAll(springVipConfiguration = springVipConfiguration
                , restConfig = restConfig
                , request = request
                , response = response)
    }
}

abstract class ARestGetById<E : Any, ID>(
        private val springVipConfiguration: SpringVipConfiguration,
        private val restConfig: ARestConfig<E, ID>
) {

    @GetMapping("/{id}")
    fun getById(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): Any {
        return restGetById(springVipConfiguration = springVipConfiguration
                , restConfig = restConfig
                , request = request
                , response = response
                , id = id)

    }
}

abstract class ARestCreate<E : Any, ID>(
        private val springVipConfiguration: SpringVipConfiguration,
        private val restConfig: ARestConfig<E, ID>
) {

    // (C) create Operations
    @PostMapping("/")
    open fun create(request: HttpServletRequest, response: HttpServletResponse, @Valid @RequestBody entity: E): Any {
        return restCreate(springVipConfiguration = springVipConfiguration
                , restConfig = restConfig
                , request = request
                , response = response
                , entity = entity)
    }
}

abstract class ARestUpdate<E : Any, ID>(
        private val springVipConfiguration: SpringVipConfiguration,
        private val restConfig: ARestConfig<E, ID>

) {

    // (U) update Operations
    @PutMapping("/")
    open fun update(request: HttpServletRequest, response: HttpServletResponse, @Valid @RequestBody entity: E): Any {
        return restUpdate(springVipConfiguration = springVipConfiguration
                , restConfig = restConfig
                , request = request
                , response = response
                , entity = entity)
    }
}

abstract class ARestDelete<E : Any, ID>(
        private val springVipConfiguration: SpringVipConfiguration,
        private val restConfig: ARestConfig<E, ID>
) {

    // (D) delete Operations
    @DeleteMapping("/{id}")
    fun delete(request: HttpServletRequest, response: HttpServletResponse, @PathVariable id: ID): Any {
        return restDelete(springVipConfiguration = springVipConfiguration
                , restConfig = restConfig
                , request = request
                , response = response
                , id = id)
    }
}