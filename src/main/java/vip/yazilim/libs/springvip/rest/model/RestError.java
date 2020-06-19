package vip.yazilim.libs.springvip.rest.model;

import lombok.Data;

/**
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
@Data
public class RestError<C> {

    private int errorCode;
    private C errorCause;

    public RestError(int errorCode, C errorCause) {
        this.errorCode = errorCode;
        this.errorCause = errorCause;
    }
}
