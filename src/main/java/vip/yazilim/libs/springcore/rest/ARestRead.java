package vip.yazilim.libs.springcore.rest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vip.yazilim.libs.springcore.rest.model.RestResponse;
import vip.yazilim.libs.springcore.service.ICrudService;

/**
 * Generic REST Controller Implementations for generic Read operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen@yazilim.vip
 */
public abstract class ARestRead<E, ID> {

	protected abstract ICrudService<E, ID> getService();

	protected abstract Class<E> getClassOfEntity();

	// (R) read Operations
	@GetMapping("/")
	public RestResponse<List<E>> getAll(HttpServletRequest request, HttpServletResponse response) {

		// get repo
		ICrudService<E, ID> crudService = getService();

		// get entity list
		List<E> entityList = null;
		entityList = crudService.getAll();

		// init response
		return RestResponse.generateResponse(entityList, HttpStatus.OK, request, response);
	}

	@GetMapping("/{id}")
	public RestResponse<E> getById(HttpServletRequest request, HttpServletResponse response, @PathVariable ID id) {

		// get repo
		ICrudService<E, ID> crudService = getService();

		// get entity
		Optional<E> entity = crudService.getById(id);
		if (!entity.isPresent()) {
			String className = getClassOfEntity().getSimpleName();
			throw new NoSuchElementException(className + " Not Found :: " + String.valueOf(id));
		}

		// init response
		return RestResponse.generateResponse(entity.get(), HttpStatus.OK, request, response);
	}

}
