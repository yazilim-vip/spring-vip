package vip.yazilim.libs.springcore.exception;

import java.util.Arrays;

public class DatabaseReadException extends DatabaseException {
    private static final long serialVersionUID = -3490767649996233498L;

    public <E, ID> DatabaseReadException(Class<E> entityClass, Exception exception) {
        super(entityClass, "READ", "", exception);
    }

    public <E, ID> DatabaseReadException(Class<E> entityClass, Exception exception, Object... params) {
        super(entityClass, "READ", Arrays.toString(params), exception);
    }
}
