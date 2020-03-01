package vip.yazilim.libs.springcore.exception.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Emre Sen, 26.06.2019
 * @contact maemresen@yazilim.vip
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class MethodNotSupported extends MsRuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 2404214411760573147L;

    public MethodNotSupported(String message) {
        super(message);
    }
}
