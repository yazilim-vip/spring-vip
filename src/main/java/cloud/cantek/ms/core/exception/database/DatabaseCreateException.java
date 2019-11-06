package cloud.cantek.ms.core.exception.database;

import cloud.cantek.ms.core.constant.MsCoreConstants;

public class DatabaseCreateException extends DatabaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1855909767677656982L;

	public DatabaseCreateException(Exception exception) {
		super(MsCoreConstants.ERROR_MESSAGE_ENTITY_CREATE, exception);
	}

}
