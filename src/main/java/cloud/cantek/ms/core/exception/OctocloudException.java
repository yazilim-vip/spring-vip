package cloud.cantek.ms.core.exception;

public class OctocloudException extends GeneralException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7752942366691303091L;

	public OctocloudException(String message, Exception exception) {
        super(message, exception);
    }

    public OctocloudException(String message) {
        super(message);
    }
}
