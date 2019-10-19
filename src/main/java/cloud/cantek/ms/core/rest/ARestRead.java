package cloud.cantek.ms.core.rest;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.HandlerMapping;

import cloud.cantek.ms.core.constant.NotifierMicroserviceConstants;
import cloud.cantek.ms.core.rest.model.RestErrorResponse;
import cloud.cantek.ms.core.rest.model.RestResponse;
import cloud.cantek.ms.core.service.ICrudService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Emre Sen, 23.07.2019
 * @contact maemresen07@gmail.com
 */
public abstract class ARestRead<E, ID> {

    protected abstract ICrudService<E, ID> getService();

    //TODO: ZZ_OTHER: list will be converted to set
    // (R) read Operations
    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Error", response = RestErrorResponse.class)
    })
    public RestResponse<List<E>> getAll(HttpServletRequest request, HttpServletResponse response) {

        // get repo
        ICrudService<E, ID> crudService = getService();

        // get entity list
        List<E> entityList = crudService.getAll();

        // init response
        return generateResponse(entityList, HttpStatus.OK, request, response);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = NotifierMicroserviceConstants.ERROR_MESSAGE_ENTITY_NOT_FOUND, response = RestErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Error", response = RestErrorResponse.class)
    })
    public RestResponse<E> getById(HttpServletRequest request, HttpServletResponse response
            , @PathVariable ID id) {

        // get repo
        ICrudService<E, ID> crudService = getService();

        // get entity
        E entity = crudService.getById(id);

        // init response
        return generateResponse(entity, HttpStatus.OK, request, response);
    }

    
    protected <B> RestResponse<B> generateResponse(B responseBody, HttpStatus httpStatus, HttpServletRequest request, HttpServletResponse response) {

        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        RestResponse<B> restResponse = new RestResponse<>(false);
        restResponse.setTimestamp(new Date().getTime());
        restResponse.setPath(path);
        restResponse.setMessage(httpStatus.getReasonPhrase());
        restResponse.setData(responseBody);

        response.setIntHeader("status", httpStatus.value());
        return restResponse;
    }

}
