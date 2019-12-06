package vip.yazilim.spring.core.exception.general.database;

import vip.yazilim.spring.core.constant.SpringCoreConstants;

/**
 * Fired when a database create exception occurred
 * 
 * @author Emre Sen - Dec 7, 2019
 * @contact maemresen@yazilim.vip
 *
 */
public class DatabaseCreateException extends DatabaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1855909767677656982L;

	public DatabaseCreateException(Exception exception) {
		super(SpringCoreConstants.ERROR_MESSAGE_ENTITY_CREATE, exception);
	}

}
