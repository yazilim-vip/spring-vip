package vip.yazilim.libs.springcore.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vip.yazilim.libs.springcore.rest.model.RestResponse;
import vip.yazilim.libs.springcore.service.ICrudService;

/**
 * Generic REST Controller Implementations for generic CRUD operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
public abstract class ARestCrud<E, ID> extends ARestCru<E, ID> {

	// (D) delete Operations
	@DeleteMapping("/{id}")
	public RestResponse<Boolean> delete(HttpServletRequest request, HttpServletResponse response, @PathVariable ID id) {

		// get repo
		ICrudService<E, ID> crudService = getService();

		// delete status
		boolean status;

		// delete entity
		status = crudService.deleteById(id);

		// init response
		return RestResponse.generateResponse(status, HttpStatus.OK, request, response);
	}

}
