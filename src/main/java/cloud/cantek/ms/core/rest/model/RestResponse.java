package cloud.cantek.ms.core.rest.model;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerMapping;

import lombok.Data;

/**
 * Generic Response model for REST Microservices.
 * 
 * @author Emre Sen, 24.07.2019
 * @contact maemresen07@gmail.com
 */
@Data
public class RestResponse<E> {

	private long timestamp;
	private String path;
	private final boolean hasError;
	private String message;

	private E data;

	public RestResponse(boolean hasError) {
		this.hasError = hasError;
	}
	
}
