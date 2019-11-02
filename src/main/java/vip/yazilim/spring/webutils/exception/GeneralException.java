package vip.yazilim.spring.webutils.exception;


public class GeneralException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7568063758377615867L;

	public GeneralException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public GeneralException(String message) {
		super(message);
	}
}
