package vip.yazilim.libs.springcore.exception.general.database;

import vip.yazilim.libs.springcore.exception.general.BusinessLogicException;

/**
 * Fired when a database exception occurred
 *
 * @author Emre Sen, 26.06.2019
 * @contact maemresen@yazilim.vip
 */
public class DatabaseException extends BusinessLogicException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5862962376829113206L;

	protected DatabaseException(String message, Exception exception) {
        super(message, exception);
    }
}
