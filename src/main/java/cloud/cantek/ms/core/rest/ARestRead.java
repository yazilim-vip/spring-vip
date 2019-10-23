package cloud.cantek.ms.core.rest;

import cloud.cantek.ms.core.constant.OctocloudMsCoreConstants;
import cloud.cantek.ms.core.rest.model.RestErrorResponse;
import cloud.cantek.ms.core.rest.model.RestResponse;
import cloud.cantek.ms.core.rest.model.RestResponseFactory;
import cloud.cantek.ms.core.service.ICrudService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Generic REST Controller Implementations for generic Read operations
 *
 * @author Emre Sen, 23.07.2019
 * @contact maemresen07@gmail.com
 */
public abstract class ARestRead<E, ID> {

    protected abstract ICrudService<E, ID> getService();

    // (R) read Operations
    @GetMapping("/")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Internal Error", response = RestErrorResponse.class)})
    public RestResponse<List<E>> getAll(HttpServletRequest request, HttpServletResponse response) {

        // get repo
        ICrudService<E, ID> crudService = getService();

        // get entity list
        List<E> entityList = crudService.getAll();

        // init response
        return RestResponseFactory.generateResponse(entityList, HttpStatus.OK, request, response);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = OctocloudMsCoreConstants.ERROR_MESSAGE_ENTITY_NOT_FOUND, response = RestErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Error", response = RestErrorResponse.class)})
    public RestResponse<E> getById(HttpServletRequest request, HttpServletResponse response, @PathVariable ID id) {

        // get repo
        ICrudService<E, ID> crudService = getService();

        // get entity
        E entity = crudService.getById(id);

        // init response
        return RestResponseFactory.generateResponse(entity, HttpStatus.OK, request, response);
    }

}
