package cloud.cantek.ms.core.exception.database;

import cloud.cantek.ms.core.constant.MsCoreConstants;

public class DatabaseDeleteException extends DatabaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7558357250959860512L;

	public DatabaseDeleteException(Exception exception) {
		super(MsCoreConstants.ERROR_MESSAGE_ENTITY_DELETE, exception);
	}

}
