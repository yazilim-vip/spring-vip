package cloud.cantek.ms.core.exception;

import cloud.cantek.ms.core.exception.GeneralException;

public class PrimaryKeyDuplicationException extends GeneralException{

	public PrimaryKeyDuplicationException(String message, Exception exception) {
		super(message, exception);
		// TODO Auto-generated constructor stub
	}
	
	public PrimaryKeyDuplicationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
