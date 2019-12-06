package vip.yazilim.spring.core.exception.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import vip.yazilim.spring.core.exception.general.GeneralException;
import vip.yazilim.spring.core.exception.general.InvalidModelException;
import vip.yazilim.spring.core.exception.general.InvalidUpdateException;
import vip.yazilim.spring.core.exception.general.database.DatabaseException;

/**
 * Fired when a Internal Server Error occurred while calling the Rest API
 * 
 * @author Emre Sen, 26.06.2019
 * @contact maemresen@yazilim.vip
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

	public ServiceException(InvalidModelException invalidModelException) {
		super("Invalid Model Error", invalidModelException);
	}

	public ServiceException(String message) {
		super(message);
	}
}
