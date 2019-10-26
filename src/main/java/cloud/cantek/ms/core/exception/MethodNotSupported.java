package cloud.cantek.ms.core.exception;

import cloud.cantek.core.exception.GeneralException;

/**
 * @author Emre Sen, 26.06.2019
 * @contact maemresen07@gmail.com
 */
public class MethodNotSupported extends GeneralException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2404214411760573147L;

	public MethodNotSupported(String message, Exception exception) {
        super(message, exception);
    }

    public MethodNotSupported(String message) {
        super(message);
    }
}
