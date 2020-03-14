package vip.yazilim.libs.springcore.exception.general.database;

import java.util.Arrays;

public class DatabaseDeleteException extends DatabaseException {
	private static final long serialVersionUID = 7558357250959860512L;

	public <E, ID> DatabaseDeleteException(Class<E> entityClass, ID id, Exception exception) {
		super(entityClass, "DELETE", String.valueOf(id), exception);
	}

	public <E, ID> DatabaseDeleteException(Class<E> entityClass, Exception exception, Object... params) {
		super(entityClass, "DELETE", Arrays.toString(params), exception);
	}
}
