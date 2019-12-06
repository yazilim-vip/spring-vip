package vip.yazilim.spring.core.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import vip.yazilim.spring.core.constant.SpringCoreConstants;
import vip.yazilim.spring.core.exception.web.ServiceException;
import vip.yazilim.spring.core.rest.model.RestErrorResponse;
import vip.yazilim.spring.core.rest.model.RestResponse;
import vip.yazilim.spring.core.service.ICrudService;
import vip.yazilim.spring.core.util.RestResponseFactory;

/**
 * Generic REST Controller Implementations for generic Read, Create and Update
 * operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
public abstract class ARestCru<E, ID> extends ARestRead<E, ID> {

    protected abstract ICrudService<E, ID> getService();

    // (C) create Operations
    @PostMapping("/")
    @CrossOrigin(origins = "*")
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal Error", response = RestErrorResponse.class)})
    public RestResponse<E> create(HttpServletRequest request, HttpServletResponse response, @RequestBody E entity) {

        // get repo
        ICrudService<E, ID> crudService = getService();

        // create entity
        try {
            entity = crudService.create(entity);
        } catch (Exception e) {
            throw new ServiceException(e);
        }

        // init response
        return RestResponseFactory.generateResponse(entity, HttpStatus.CREATED, request, response);
    }

    // (U) update Operations
    @PutMapping("/")
    @CrossOrigin(origins = "*")
    @ApiResponses(value = {
        @ApiResponse(code = 404, message = SpringCoreConstants.ERROR_MESSAGE_ENTITY_NOT_FOUND, response = RestErrorResponse.class),
        @ApiResponse(code = 500, message = "Internal Error", response = RestErrorResponse.class)})
    public RestResponse<E> update(HttpServletRequest request, HttpServletResponse response, @RequestBody E entity) {

        // get repo
        ICrudService<E, ID> crudService = getService();

        // update entity
        try {
            entity = crudService.update(entity);
        } catch (Exception e) {
            throw new ServiceException(e);
        }

        // init response
        return RestResponseFactory.generateResponse(entity, HttpStatus.OK, request, response);
    }
}
