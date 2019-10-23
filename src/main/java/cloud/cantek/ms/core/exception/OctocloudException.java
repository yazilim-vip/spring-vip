package cloud.cantek.ms.core.exception;

public class OctocloudException extends GeneralException {
    public OctocloudException(String message, Exception exception) {
        super(message, exception);
    }

    public OctocloudException(String message) {
        super(message);
    }
}
