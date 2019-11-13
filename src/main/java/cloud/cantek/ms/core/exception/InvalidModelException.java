package cloud.cantek.ms.core.exception;

public class InvalidModelException extends GeneralException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidModelException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public InvalidModelException(String message) {
		super(message);
	}

	
}
