package vip.yazilim.spring.core.exception.database;

import vip.yazilim.spring.core.constant.SpringCoreConstants;

public class DatabaseDeleteException extends DatabaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7558357250959860512L;

	public DatabaseDeleteException(Exception exception) {
		super(SpringCoreConstants.ERROR_MESSAGE_ENTITY_DELETE, exception);
	}

}
