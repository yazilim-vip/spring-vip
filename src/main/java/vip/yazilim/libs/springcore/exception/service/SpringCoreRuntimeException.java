package vip.yazilim.libs.springcore.exception.service;

/**
 * @author Emre Sen, 26.06.2019
 * @contact maemresen@yazilim.vip
 */
class SpringCoreRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected SpringCoreRuntimeException(String message, Exception exception) {
        super(message + " :: " + exception.getMessage(), exception);
    }

    protected SpringCoreRuntimeException(String message) {
        super(message);
    }

}


