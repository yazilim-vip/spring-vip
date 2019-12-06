package vip.yazilim.spring.core.exception.general;

/**
 * Fired when a system based general exception occurred
 * 
 * @author Emre Sen - Dec 7, 2019
 * @contact maemresen@yazilim.vip
 *
 */
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
