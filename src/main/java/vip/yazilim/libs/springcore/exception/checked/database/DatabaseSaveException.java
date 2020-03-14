package vip.yazilim.libs.springcore.exception.checked.database;

public class DatabaseSaveException extends DatabaseException {
    private static final long serialVersionUID = -1855909767677656982L;

    public <E, ID> DatabaseSaveException(Class<E> entityClass, ID id, Exception exception) {
        super(entityClass, "SAVE", String.valueOf(id), exception);
    }
}