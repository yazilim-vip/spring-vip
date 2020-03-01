package vip.yazilim.libs.springcore.exception.general.database;

public class DatabaseUpdateException extends DatabaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2541270298758020598L;

	public DatabaseUpdateException(Exception exception) {
		super("An error occurred while saving Entity", exception);
	}

}
