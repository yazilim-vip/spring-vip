package vip.yazilim.libs.springcore.exception.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import vip.yazilim.libs.springcore.exception.general.BusinessLogicException;
import vip.yazilim.libs.springcore.exception.general.InvalidModelException;
import vip.yazilim.libs.springcore.exception.general.database.DatabaseException;
/**
 * @author Emre Sen, 26.06.2019
 * @contact maemresen@yazilim.vip
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class RestException extends SpringCoreRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -790091943088990641L;

	public RestException(String message, Exception exception) {
		super(message, exception);
	}

	public RestException(Exception exception) {
		super("Database Error", exception);
	}

	public RestException(DatabaseException databaseException) {
		super("Database Error", databaseException);
	}

	public RestException(BusinessLogicException generalException) {
		super("System Error", generalException);
	}

	public RestException(InvalidModelException invalidModelException) {
		super("Invalid Model Error", invalidModelException);
	}

	public RestException(String message) {
		super(message);
	}
}
