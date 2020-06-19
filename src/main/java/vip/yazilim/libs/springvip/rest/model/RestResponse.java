package vip.yazilim.libs.springvip.rest.model;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Generic Response model for REST Microservices.
 *
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
@Data
public class RestResponse<E> {

    private long timestamp;
    private String path;
    private final boolean hasError;
    private String message;

    private E data;

    public RestResponse() {
        this(false);
    }

    public RestResponse(boolean hasError) {
        this.hasError = hasError;
    }


    public static <B> RestResponse<B> generateResponse(B responseBody, HttpStatus httpStatus, HttpServletRequest request,
                                                       HttpServletResponse response) {

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
