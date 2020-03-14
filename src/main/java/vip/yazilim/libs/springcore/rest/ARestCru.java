package vip.yazilim.libs.springcore.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import vip.yazilim.libs.springcore.rest.model.RestResponse;
import vip.yazilim.libs.springcore.service.ICrudService;

/**
 * Generic REST Controller Implementations for generic CRUD operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
public abstract class ARestCru<E, ID> extends ARestRead<E, ID> {

	// (C) create Operations
	@PostMapping("/")
	public RestResponse<E> create(HttpServletRequest request, HttpServletResponse response, @RequestBody E entity) {

		// get repo
		ICrudService<E, ID> crudService = getService();

		// create entity
		E createdEntity = crudService.create(entity);

		// init response
		return RestResponse.generateResponse(createdEntity, HttpStatus.CREATED, request, response);
	}

	// (U) update Operations
	@PutMapping("/")
	public RestResponse<E> update(HttpServletRequest request, HttpServletResponse response, @RequestBody E entity) {

		// get repo
		ICrudService<E, ID> crudService = getService();

		// update entity
		E updatedEntity = crudService.update(entity);

		// init response
		return RestResponse.generateResponse(updatedEntity, HttpStatus.OK, request, response);
	}

}
