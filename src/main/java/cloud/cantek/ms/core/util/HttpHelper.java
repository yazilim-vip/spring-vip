package cloud.cantek.ms.core.util;

import cloud.cantek.ms.core.rest.model.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Emre Sen, 26.07.2019
 * @contact maemresen07@gmail.com
 */
public class HttpHelper {

    public static <B> RestResponse<B> generateResponse(B responseBody, HttpStatus httpStatus, HttpServletRequest request, HttpServletResponse response) {

        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        RestResponse<B> restResponse = new RestResponse<>(false);
        restResponse.setTimestamp(new Date().getTime());
        restResponse.setPath(path);
        restResponse.setMessage(httpStatus.getReasonPhrase());
        restResponse.setData(responseBody);

        response.setIntHeader("status", httpStatus.value());
        return restResponse;
    }

}
