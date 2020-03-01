package vip.yazilim.libs.springcore.exception.general;

public class GeneralException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7568063758377615867L;

	public GeneralException(String message, Throwable throwable) {
		super(message + " :: " + throwable.getMessage(), throwable);
	}

	public GeneralException(String message) {
		super(message);
	}
}
