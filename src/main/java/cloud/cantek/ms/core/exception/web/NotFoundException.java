package cloud.cantek.ms.core.exception.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import cloud.cantek.ms.core.exception.GeneralException;

/**
 * @author Emre Sen, 26.06.2019
 * @contact maemresen07@gmail.com
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends MsRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9109984729627077232L;

	
	public NotFoundException(GeneralException generalException) {
		super("Entity Not Found", generalException);
	}

	public NotFoundException() {
		super("Entity Not Found");
	}

}
