package vip.yazilim.libs.springcore.exception.general.database;

public class DatabaseCreateException extends DatabaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1855909767677656982L;

	public DatabaseCreateException(Exception exception) {
		super("An error occurred while saving Entity", exception);
	}

}
