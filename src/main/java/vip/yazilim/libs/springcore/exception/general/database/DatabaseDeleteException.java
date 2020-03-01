package vip.yazilim.libs.springcore.exception.general.database;

public class DatabaseDeleteException extends DatabaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7558357250959860512L;

	public DatabaseDeleteException(Exception exception) {
		super("An error occurred while deleting Entity", exception);
	}

}
