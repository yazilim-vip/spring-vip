package vip.yazilim.libs.springcore.exception.general;

public class ThreadException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7568063758377615867L;

	public ThreadException(String message, Throwable throwable) {
		super(message + " :: " + throwable.getMessage(), throwable);
	}

	public ThreadException(String message) {
		super(message);
	}
}
