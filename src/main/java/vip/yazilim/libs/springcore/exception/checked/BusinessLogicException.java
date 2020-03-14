package vip.yazilim.libs.springcore.exception.checked;

public class BusinessLogicException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7568063758377615867L;

	public BusinessLogicException(String message, Throwable throwable) {
		super(message + " :: " + throwable.getMessage(), throwable);
	}

	public BusinessLogicException(String message) {
		super(message);
	}
}
