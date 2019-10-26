package cloud.cantek.ms.core.exception.runtime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import cloud.cantek.core.exception.*;
import cloud.cantek.ms.core.exception.DatabaseException;

/**
 * @author Emre Sen, 26.06.2019
 * @contact maemresen07@gmail.com
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException(String message, Exception exception) {
		super(message + " : " + ((exception == null) ? "" : exception.getMessage()));
	}

	public ServiceException(String message) {
		this(message, null);
	}

	public ServiceException(DatabaseException databaseException) {
		this("Database Error", databaseException);
	}

	public  ServiceException(GeneralException generalException){
		this("System Error", generalException);
	}
}


