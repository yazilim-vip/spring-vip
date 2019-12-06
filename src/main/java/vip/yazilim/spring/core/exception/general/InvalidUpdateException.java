package vip.yazilim.spring.core.exception.general;

/**
 * Fired when an exception occurred during update process
 *
 * @author Emre Sen, 26.06.2019
 * @contact maemresen07@gmail.com
 */
public class InvalidUpdateException extends GeneralException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5862962376829113206L;

	public InvalidUpdateException(String message) {
		super(message);
	}
}
