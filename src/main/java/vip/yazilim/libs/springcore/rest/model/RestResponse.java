package vip.yazilim.libs.springcore.rest.model;

import lombok.Data;

/**
 * Generic Response model for REST Microservices.
 * 
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
@Data
public class RestResponse<E> {

    private long timestamp;
    private String path;
    private final boolean hasError;
    private String message;

    private E data;

    public RestResponse() {
        this(false);
    }

    public RestResponse(boolean hasError) {
        this.hasError = hasError;
    }

}
