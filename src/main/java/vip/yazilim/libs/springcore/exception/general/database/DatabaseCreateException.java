package vip.yazilim.libs.springcore.exception.general.database;

public class DatabaseCreateException extends DatabaseException {
	
	private static final long serialVersionUID = -1855909767677656982L;

	public <E, ID> DatabaseCreateException(Class<E> entityClass, ID id, Exception exception) {
		super(entityClass, "CREATE", String.valueOf(id), exception);
	}
}
