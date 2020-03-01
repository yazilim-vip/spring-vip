package vip.yazilim.libs.springcore.exception.general.database;

public class DatabaseReadException extends DatabaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3490767649996233498L;

	public DatabaseReadException(Exception exception) {
		super("An error occurred while fetching Entity", exception);
	}
}
