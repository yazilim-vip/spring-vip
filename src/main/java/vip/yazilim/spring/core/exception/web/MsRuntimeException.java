package vip.yazilim.spring.core.exception.web;

/**
 * Parent Exception Class
 * 
 * @author Emre Sen, 26.06.2019
 * @contact maemresen07@gmail.com
 */
public class MsRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected MsRuntimeException(String message, Exception exception) {
		super(message + " :: " + exception.getMessage(), exception);
	}

	protected MsRuntimeException(String message) {
		super(message);
	}

}
