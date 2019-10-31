package cloud.cantek.ms.core.exception.runtime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import cloud.cantek.core.exception.GeneralException;
import cloud.cantek.ms.core.exception.DatabaseException;

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
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Exception exception) {
        super("Database Error", exception);
    }
	
    public ServiceException(DatabaseException databaseException) {
        super("Database Error", databaseException);
    }

    public ServiceException(GeneralException generalException) {
        super("System Error", generalException);
    }

	public ServiceException(String message) {
		super(message);
	}
}


