package vip.yazilim.libs.springcore.exception.general;

public class InvalidModelException extends BusinessLogicException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidModelException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public InvalidModelException(String message) {
		super(message);
	}

	
}
