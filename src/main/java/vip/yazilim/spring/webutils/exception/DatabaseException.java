package vip.yazilim.spring.webutils.exception;

/**
 * Fired when a database exception occurred
 *
 * @author Emre Sen, 26.06.2019
 * @contact maemresen07@gmail.com
 */
public class DatabaseException extends GeneralException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5862962376829113206L;

	public DatabaseException(String message, Exception exception) {
        super(message, exception);
    }
}