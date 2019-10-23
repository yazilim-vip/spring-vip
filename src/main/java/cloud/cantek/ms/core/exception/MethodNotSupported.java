package cloud.cantek.ms.core.exception;

/**
 * @author Emre Sen, 26.06.2019
 * @contact maemresen07@gmail.com
 */
public class MethodNotSupported extends GeneralException {

    public MethodNotSupported(String message, Exception exception) {
        super(message, exception);
    }

    public MethodNotSupported(String message) {
        super(message);
    }
}
