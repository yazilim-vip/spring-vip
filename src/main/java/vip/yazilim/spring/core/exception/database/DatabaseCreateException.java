package vip.yazilim.spring.core.exception.database;

import vip.yazilim.spring.core.constant.SpringCoreConstants;

public class DatabaseCreateException extends DatabaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1855909767677656982L;

	public DatabaseCreateException(Exception exception) {
		super(SpringCoreConstants.ERROR_MESSAGE_ENTITY_CREATE, exception);
	}

}
