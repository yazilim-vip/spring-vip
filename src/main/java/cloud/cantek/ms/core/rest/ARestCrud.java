package cloud.cantek.ms.core.rest;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cloud.cantek.ms.core.constant.MsCoreConstants;
import cloud.cantek.ms.core.exception.GeneralException;
import cloud.cantek.ms.core.exception.InvalidUpdateException;
import cloud.cantek.ms.core.exception.database.DatabaseException;
import cloud.cantek.ms.core.exception.web.NotFoundException;
import cloud.cantek.ms.core.exception.web.ServiceException;
import cloud.cantek.ms.core.rest.model.RestErrorResponse;
import cloud.cantek.ms.core.rest.model.RestResponse;
import cloud.cantek.ms.core.rest.model.RestResponseFactory;
import cloud.cantek.ms.core.service.ICrudService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Generic REST Controller Implementations for generic CRUD operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen07@gmail.com
 */
public abstract class ARestCrud<E, ID> {

    protected abstract ICrudService<E, ID> getService();

    // (C) create Operations
    @PostMapping("/")
    @CrossOrigin(origins = "*")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Internal Error", response = RestErrorResponse.class)})
    public RestResponse<E> create(HttpServletRequest request, HttpServletResponse response, @RequestBody E entity) {

        // get repo
        ICrudService<E, ID> crudService = getService();

        // create entity
        try {
            entity = crudService.create(entity);
        } catch (GeneralException e) {
            throw new ServiceException(e);
        }

        // init response
        return RestResponseFactory.generateResponse(entity, HttpStatus.CREATED, request, response);
    }

    // (U) update Operations
    @PutMapping("/")
	@CrossOrigin(origins = "*")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = MsCoreConstants.ERROR_MESSAGE_ENTITY_NOT_FOUND, response = RestErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Error", response = RestErrorResponse.class)})
    public RestResponse<E> update(HttpServletRequest request, HttpServletResponse response, @RequestBody E entity) {

        // get repo
        ICrudService<E, ID> crudService = getService();

        // update entity
        try {
            entity = crudService.update(entity);
        } catch (DatabaseException | InvalidUpdateException e) {
            throw new ServiceException(e);
        }

        // init response
        return RestResponseFactory.generateResponse(entity, HttpStatus.OK, request, response);
    }

    // (R) read Operations
    @GetMapping("/")
	@CrossOrigin(origins = "*")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Internal Error", response = RestErrorResponse.class)})
    public RestResponse<List<E>> getAll(HttpServletRequest request, HttpServletResponse response) {

        // get repo
        ICrudService<E, ID> crudService = getService();

        // get entity list
        List<E> entityList = null;
        try {
            entityList = crudService.getAll();
        } catch (DatabaseException e) {
            throw new ServiceException(e);
        }

        // init response
        return RestResponseFactory.generateResponse(entityList, HttpStatus.OK, request, response);
    }

    @GetMapping("/{id}")
	@CrossOrigin(origins = "*")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = MsCoreConstants.ERROR_MESSAGE_ENTITY_NOT_FOUND, response = RestErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Error", response = RestErrorResponse.class)})
    public RestResponse<E> getById(HttpServletRequest request, HttpServletResponse response, @PathVariable ID id) {

        // get repo
        ICrudService<E, ID> crudService = getService();

        // get entity
        Optional<E> entity = null;
        try {
            entity = crudService.getById(id);
        } catch (DatabaseException e) {
            throw new ServiceException(e);
        }
        
        if(!entity.isPresent()) {
        	throw new NotFoundException("Entity Not Found");
        }
        
        // init response
        return RestResponseFactory.generateResponse(entity.get(), HttpStatus.OK, request, response);
    }
}
