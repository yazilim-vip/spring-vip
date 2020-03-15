package vip.yazilim.libs.springcore.exception;

public class DatabaseCreateException extends DatabaseException {
	
	private static final long serialVersionUID = -1855909767677656982L;

	public <E, ID> DatabaseCreateException(Class<E> entityClass, Exception exception) {
		super(entityClass, "CREATE", "", exception);
	}
}
