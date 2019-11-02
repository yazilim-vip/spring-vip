package vip.yazilim.spring.webutils.rest.model;

/**
 * @author Emre Sen, 24.07.2019
 * @contact maemresen07@gmail.com
 */

public class RestErrorResponse extends RestResponse<RestError> {
    public RestErrorResponse() {
        super(true);
    }
}
