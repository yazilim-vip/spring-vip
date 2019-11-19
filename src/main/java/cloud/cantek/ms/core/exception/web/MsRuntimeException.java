package cloud.cantek.ms.core.exception.web;

/**
 * @author Emre Sen, 26.06.2019
 * @contact maemresen07@gmail.com
 */
public class MsRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MsRuntimeException(String message, Exception exception) {
        this(message + " :: " + exception.getMessage());
    } 
    
    public MsRuntimeException(String message) {
        super(message);
    }

}


