package cloud.cantek.ms.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Emre Sen, 26.06.2019
 * @contact maemresen07@gmail.com
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class MethodNotSupported extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MethodNotSupported() {
        super("Method not Supported");
    }

}
