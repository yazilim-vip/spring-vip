package vip.yazilim.libs.springcore.util;

import java.net.URI;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriComponentsBuilder;

import vip.yazilim.libs.springcore.rest.model.RestResponse;

/**
 * @author Emre Sen, 26.07.2019
 * @contact maemresen@yazilim.vip
 */
public class HttpHelper {

    public static <B> RestResponse<B> generateResponse(B responseBody, HttpStatus httpStatus,
                                                       HttpServletRequest request, HttpServletResponse response) {

        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        RestResponse<B> restResponse = new RestResponse<>(false);
        restResponse.setTimestamp(new Date().getTime());
        restResponse.setPath(path);
        restResponse.setMessage(httpStatus.getReasonPhrase());
        restResponse.setData(responseBody);

        response.setIntHeader("status", httpStatus.value());
        return restResponse;
    }

    public static <R> R getJsonRequest(String baseUri, String resource
            , ParameterizedTypeReference<R> typeReference
            , Map<String, String> urlParamMap
            , MultiValueMap<String, String> queryParamMap) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        
        URI uri = UriComponentsBuilder
                .fromUriString(baseUri + resource)
                .queryParams(queryParamMap)
                .buildAndExpand(urlParamMap)
                .encode()
                .toUri();
        
        ResponseEntity<R> httpResponseEntity = restTemplate.exchange(uri, HttpMethod.GET, request, typeReference);
        return httpResponseEntity.getBody();
    }

    
}
