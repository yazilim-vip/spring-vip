package vip.yazilim.spring.core.exception.general;

/**
 * Fired when an Invalid argument provided for the method or constructor etc.
 *
 * @author Emre Sen - Dec 7, 2019
 * @contact maemresen@yazilim.vip
 *
 */
public class InvalidArgumentException extends GeneralException {

    /**
     *
     */
    private static final long serialVersionUID = -3070965916403502456L;

    public InvalidArgumentException(String message) {
        super(message);
    }

}
