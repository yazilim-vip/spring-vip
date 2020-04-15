package vip.yazilim.libs.springcore.rest.model;

/**
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
public class RestErrorResponse extends RestResponse<RestError<?>> {

    public RestErrorResponse() {
        super(true);
    }
}
