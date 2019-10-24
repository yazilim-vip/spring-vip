package cloud.cantek.ms.core.exception;

public class GeneralException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7568063758377615867L;

	public GeneralException(String message, Exception exception) {
		super(message + " : " + ((exception == null) ? "" : exception.getMessage()));
	}

	public GeneralException(String message) {
		this(message, null);
	}

}
