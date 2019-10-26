package cloud.cantek.ms.core.exception;

import cloud.cantek.core.exception.GeneralException;

/**
 * Fired when a database exception occurred
 *
 * @author Emre Sen, 26.06.2019
 * @contact maemresen07@gmail.com
 */
public class UnambiguousStateException extends GeneralException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5862962376829113206L;

	public UnambiguousStateException(String message, Exception exception) {
        super(message, exception);
    }

    public UnambiguousStateException(String message) {
        super(message);
    }
}
