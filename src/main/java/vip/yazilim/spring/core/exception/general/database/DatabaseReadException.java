package vip.yazilim.spring.core.exception.general.database;

import vip.yazilim.spring.core.constant.SpringCoreConstants;

/**
 * Fired when a database read exception occurred
 *
 * @author Emre Sen - Dec 7, 2019
 * @contact maemresen@yazilim.vip
 *
 */
public class DatabaseReadException extends DatabaseException {

    /**
     *
     */
    private static final long serialVersionUID = -3490767649996233498L;

    public DatabaseReadException(Exception exception) {
        super(SpringCoreConstants.ERROR_MESSAGE_ENTITY_READ, exception);
    }

    public DatabaseReadException(String errorMessage, Exception exception) {
        super(errorMessage, exception);
    }

}
