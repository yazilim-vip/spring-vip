package vip.yazilim.libs.springvip.exception;

/**
 * Fired when a database exception occurred
 *
 * @author Emre Sen, 26.06.2019
 * @contact maemresen@yazilim.vip
 */
public class DatabaseException extends RuntimeException {
    private static final long serialVersionUID = 5862962376829113206L;

    protected <E> DatabaseException(Class<E> entityClass, String type, String description, Exception exception) {
        super(entityClass.getSimpleName() + " :: " + type + " Error :: " + description, exception);
    }
}
