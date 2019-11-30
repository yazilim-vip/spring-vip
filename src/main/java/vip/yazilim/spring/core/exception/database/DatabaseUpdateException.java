package vip.yazilim.spring.core.exception.database;

import vip.yazilim.spring.core.constant.SpringCoreConstants;

public class DatabaseUpdateException extends DatabaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2541270298758020598L;

	public DatabaseUpdateException(Exception exception) {
		super(SpringCoreConstants.ERROR_MESSAGE_ENTITY_UPDATE, exception);
	}

	public DatabaseUpdateException(String errorMessage, Exception exception) {
		super(errorMessage, exception);
	}

}
