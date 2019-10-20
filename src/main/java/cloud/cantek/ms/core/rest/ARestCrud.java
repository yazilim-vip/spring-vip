package cloud.cantek.ms.core.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cloud.cantek.ms.core.constant.OctocloudMsCoreConstants;
import cloud.cantek.ms.core.rest.model.RestErrorResponse;
import cloud.cantek.ms.core.rest.model.RestResponse;
import cloud.cantek.ms.core.service.ICrudService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Generic REST Controller Implementations for generic CRUD operations
 * 
 * @author Emre Sen, 23.07.2019
 * @contact maemresen07@gmail.com
 */
public abstract class ARestCrud<E, ID> extends ARestRead<E, ID> {

	protected abstract ICrudService<E, ID> getService();

	// (C) create Operations
	@PostMapping("/")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Error", response = RestErrorResponse.class) })
	public RestResponse<E> create(HttpServletRequest request, HttpServletResponse response, @RequestBody E entity) {

		// get repo
		ICrudService<E, ID> crudService = getService();

		// create entity
		entity = crudService.create(entity);

		// init response
		return generateResponse(entity, HttpStatus.CREATED, request, response);
	}

	// (U) update Operations

	@PutMapping("/")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = OctocloudMsCoreConstants.ERROR_MESSAGE_ENTITY_NOT_FOUND, response = RestErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Error", response = RestErrorResponse.class) })
	public RestResponse<E> update(HttpServletRequest request, HttpServletResponse response, @RequestBody E entity) {

		// get repo
		ICrudService<E, ID> crudService = getService();

		// update entity
		entity = crudService.update(entity);

		// init response
		return generateResponse(entity, HttpStatus.OK, request, response);
	}

}
