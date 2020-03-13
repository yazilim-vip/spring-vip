package vip.yazilim.libs.springcore.exception.general;

/**
 * Fired when a database exception occurred
 *
 * @author Emre Sen, 26.06.2019
 * @contact maemresen@yazilim.vip
 */
public class InvalidUpdateException extends BusinessLogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5862962376829113206L;

	public InvalidUpdateException(String message) {
		super(message);
	}
}
