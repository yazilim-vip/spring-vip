package vip.yazilim.libs.springcore.util;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriComponentsBuilder;
import vip.yazilim.libs.springcore.rest.model.RestResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Date;
import java.util.Map;

/**
 * @author Emre Sen, 26.07.2019
 * @contact maemresen@yazilim.vip
 */
public class HttpRequestMaker {

    public <B, R> R getRequest(String baseUri, String resource
            , B body
            , ParameterizedTypeReference<R> typeReference
            , Map<String, String> urlParamMap
            , MultiValueMap<String, String> queryParamMap
            , HttpMethod httpMethod) {
        return jsonRequest(baseUri, resource, body, typeReference, urlParamMap, queryParamMap, HttpMethod.GET);
    }

    public <B, R> R putRequest(String baseUri, String resource
            , B body
            , ParameterizedTypeReference<R> typeReference
            , Map<String, String> urlParamMap
            , MultiValueMap<String, String> queryParamMap
            , HttpMethod httpMethod) {
        return jsonRequest(baseUri, resource, body, typeReference, urlParamMap, queryParamMap, HttpMethod.PUT);
    }

    public <B, R> R postRequest(String baseUri, String resource
            , B body
            , ParameterizedTypeReference<R> typeReference
            , Map<String, String> urlParamMap
            , MultiValueMap<String, String> queryParamMap
            , HttpMethod httpMethod) {
        return jsonRequest(baseUri, resource, body, typeReference, urlParamMap, queryParamMap, HttpMethod.POST);
    }

    public <B, R> R deleteRequest(String baseUri, String resource
            , B body
            , ParameterizedTypeReference<R> typeReference
            , Map<String, String> urlParamMap
            , MultiValueMap<String, String> queryParamMap
            , HttpMethod httpMethod) {
        return jsonRequest(baseUri, resource, body, typeReference, urlParamMap, queryParamMap, HttpMethod.DELETE);
    }

    public <B, R> R jsonRequest(String baseUri, String resource
            , B body
            , ParameterizedTypeReference<R> typeReference
            , Map<String, String> urlParamMap
            , MultiValueMap<String, String> queryParamMap
            , HttpMethod httpMethod) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<B> request = new HttpEntity<>(body, headers);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUri + resource)
                .queryParams(queryParamMap)
                .buildAndExpand(urlParamMap)
                .encode()
                .toUri();

        ResponseEntity<R> httpResponseEntity = restTemplate.exchange(uri, httpMethod, request, typeReference);
        return httpResponseEntity.getBody();
    }
}
