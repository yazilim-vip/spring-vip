package vip.yazilim.spring.core.exception.general;

/**
 * The model received as a parameter is not a vaild model
 *
 * @author Emre Sen - Dec 7, 2019
 * @contact maemresen@yazilim.vip
 *
 */
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
