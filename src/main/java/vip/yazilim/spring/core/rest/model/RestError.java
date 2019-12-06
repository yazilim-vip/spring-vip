package vip.yazilim.spring.core.rest.model;

import lombok.Data;

/**
 * The model that represetn
 *
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
@Data
public class RestError {

    private String message;

    public RestError(String message) {
        this.message = message;
    }
}
