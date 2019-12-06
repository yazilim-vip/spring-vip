package vip.yazilim.spring.core.exception.general.database;

import vip.yazilim.spring.core.constant.SpringCoreConstants;

/**
 * Fired when a database update exception occurred
 *
 * @author Emre Sen - Dec 7, 2019
 * @contact maemresen@yazilim.vip
 *
 */
public class DatabaseUpdateException extends DatabaseException {

    /**
     *
     */
    private static final long serialVersionUID = 2541270298758020598L;

    public DatabaseUpdateException(Exception exception) {
        super(SpringCoreConstants.ERROR_MESSAGE_ENTITY_UPDATE, exception);
    }

    public DatabaseUpdateException(String errorMessage, Exception exception) {
        super(errorMessage, exception);
    }

}
