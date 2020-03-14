package vip.yazilim.libs.springcore.exception.unchecked;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Emre Sen, 26.06.2019
 * @contact maemresen@yazilim.vip
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends SpringCoreRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9109984729627077232L;

	

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
