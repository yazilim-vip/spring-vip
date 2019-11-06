package cloud.cantek.ms.core.exception.database;

import cloud.cantek.ms.core.constant.MsCoreConstants;

public class DatabaseUpdateException extends DatabaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2541270298758020598L;

	public DatabaseUpdateException(Exception exception) {
		super(MsCoreConstants.ERROR_MESSAGE_ENTITY_UPDATE, exception);
	}

}
