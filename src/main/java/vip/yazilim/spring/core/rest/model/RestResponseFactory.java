package vip.yazilim.spring.core.rest.model;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerMapping;

/**
 * Utility class to gneerate RestResponse model instance.
 * 
 * @author Emre Sen - Dec 7, 2019
 * @contact maemresen@yazilim.vip
 *
 */
public class RestResponseFactory {

	public static <B> RestResponse<B> generateResponse(B responseBody, HttpStatus httpStatus,
			HttpServletRequest request, HttpServletResponse response) {

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