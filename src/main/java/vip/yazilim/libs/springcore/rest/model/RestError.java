package vip.yazilim.libs.springcore.rest.model;

import lombok.Data;

/**
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
@Data
public class RestError {

    private int errorCode;
    private String message;

    public RestError(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
