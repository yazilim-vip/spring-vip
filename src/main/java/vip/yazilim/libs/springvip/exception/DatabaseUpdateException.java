package vip.yazilim.libs.springvip.exception;

public class DatabaseUpdateException extends DatabaseException {
    private static final long serialVersionUID = 2541270298758020598L;

    public <E, ID> DatabaseUpdateException(Class<E> entityClass, ID id, Exception exception) {
        super(entityClass, "UPDATE", String.valueOf(id), exception);
    }
}
