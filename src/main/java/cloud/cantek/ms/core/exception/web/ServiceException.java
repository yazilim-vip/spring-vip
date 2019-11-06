package cloud.cantek.ms.core.exception.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import cloud.cantek.ms.core.exception.GeneralException;
import cloud.cantek.ms.core.exception.InvalidUpdateException;
import cloud.cantek.ms.core.exception.database.DatabaseException;

/**
 * @author Emre Sen, 26.06.2019
 * @contact maemresen07@gmail.com
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceException extends MsRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -790091943088990641L;

	public ServiceException(String message, Exception exception) {
		super(message, exception);
	}

	public ServiceException(Exception exception) {
		super("Database Error", exception);
	}

	public ServiceException(DatabaseException databaseException) {
		super("Database Error", databaseException);
	}

	public ServiceException(InvalidUpdateException invalidUpdateException) {
		super("Database Error", invalidUpdateException);
	}

	public ServiceException(GeneralException generalException) {
		super("System Error", generalException);
	}

	public ServiceException(String message) {
		super(message);
	}
}
