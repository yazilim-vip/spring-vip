package vip.yazilim.spring.core.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerMapping;

import vip.yazilim.spring.core.rest.model.RestResponse;

/**
 * Utility class to gneerate RestResponse model instance.
 * 
 * @author Emre Sen - Dec 7, 2019
 * @contact maemresen@yazilim.vip
 *
 */
public class RestResponseFactory {

	/**
	 * To generate a RestResponseModel
	 * 
	 * @param responseBody body of the response
	 * @param httpStatus   status code of response 404|500|... etc
	 * @param request      incoming request made by client
	 * @param response     reponse will be sent by the Servlet to the client
	 * @return generated RestResponseModel
	 */
	public static <B> RestResponse<B> generateResponse(B responseBody, HttpStatus httpStatus,
			HttpServletRequest request, HttpServletResponse response) {

		// to get requested path from URL
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

		// create an empty model
		RestResponse<B> restResponse = new RestResponse<>(false);
		restResponse.setTimestamp(new Date().getTime());
		restResponse.setPath(path);
		restResponse.setMessage(httpStatus.getReasonPhrase());
		restResponse.setData(responseBody);

		// append response status to the header of the response
		response.setIntHeader("status", httpStatus.value());
		return restResponse;
	}

}
