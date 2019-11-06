package cloud.cantek.ms.core.exception.database;

import cloud.cantek.ms.core.constant.MsCoreConstants;

public class DatabaseReadException extends DatabaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3490767649996233498L;

	public DatabaseReadException(Exception exception) {
		super(MsCoreConstants.ERROR_MESSAGE_ENTITY_READ, exception);
	}
}
