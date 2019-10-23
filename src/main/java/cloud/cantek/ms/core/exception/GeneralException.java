package cloud.cantek.ms.core.exception;

public class GeneralException extends Exception {

    public GeneralException(String message, Exception exception) {
        super(message + " : " + ((exception == null) ? "" : exception.getMessage()));
    }

    public GeneralException(String message) {
        this(message, null);
    }


}
