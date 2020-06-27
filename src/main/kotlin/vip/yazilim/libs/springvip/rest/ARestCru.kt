package vip.yazilim.libs.springvip.rest

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import vip.yazilim.libs.springvip.config.ARestConfig
import vip.yazilim.libs.springvip.config.IRestResponseGenerator
import vip.yazilim.libs.springvip.config.SpringVipConfig
import vip.yazilim.libs.springvip.rest.model.RestResponse
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
abstract class ARestCru<E : Any, ID>(private val restConfig: ARestConfig<E, ID>) : ARestRead<E, ID>(restConfig) {

    // (C) create Operations
    @PostMapping("/")
    open fun create(request: HttpServletRequest, response: HttpServletResponse, @Valid @RequestBody entity: E): Any {
        return restCreate(restConfig = restConfig
                , request = request
                , response = response
                , entity = entity)
    }

    // (U) update Operations
    @PutMapping("/")
    open fun update(request: HttpServletRequest, response: HttpServletResponse, @Valid @RequestBody entity: E): Any {
        return restUpdate(restConfig = restConfig
                , request = request
                , response = response
                , entity = entity)
    }


}