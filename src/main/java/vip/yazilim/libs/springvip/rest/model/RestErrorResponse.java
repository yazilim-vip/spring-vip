package vip.yazilim.libs.springvip.rest.model;

/**
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
public class RestErrorResponse extends RestResponse<RestError<?>> {

    public RestErrorResponse() {
        super(true);
    }
}
