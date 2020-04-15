package vip.yazilim.libs.springcore.repo.rest;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import vip.yazilim.libs.springcore.exception.RestApiCallError;

/**
 * @author Emre Sen, 26.07.2019
 * @contact maemresen@yazilim.vip
 */
public abstract class HttpRequestMaker {

    
    public <B, R> R getRequest(String baseUri, String resource
            , B body
            , ParameterizedTypeReference<R> typeReference
            , Map<String, String> urlParamMap
            , MultiValueMap<String, String> queryParamMap) throws RestApiCallError {
        return jsonRequest(baseUri, resource, body, typeReference, urlParamMap, queryParamMap, HttpMethod.GET);
    }

    public <B, R> R putRequest(String baseUri, String resource
            , B body
            , ParameterizedTypeReference<R> typeReference
            , Map<String, String> urlParamMap
            , MultiValueMap<String, String> queryParamMap) throws RestApiCallError {
        return jsonRequest(baseUri, resource, body, typeReference, urlParamMap, queryParamMap, HttpMethod.PUT);
    }

    public <B, R> R postRequest(String baseUri, String resource
            , B body
            , ParameterizedTypeReference<R> typeReference
            , Map<String, String> urlParamMap 
            , MultiValueMap<String, String> queryParamMap) throws RestApiCallError {
        return jsonRequest(baseUri, resource, body, typeReference, urlParamMap, queryParamMap, HttpMethod.POST);
    }

    public <B, R> R deleteRequest(String baseUri, String resource
            , B body
            , ParameterizedTypeReference<R> typeReference
            , Map<String, String> urlParamMap
            , MultiValueMap<String, String> queryParamMap) throws RestApiCallError {
        return jsonRequest(baseUri, resource, body, typeReference, urlParamMap, queryParamMap, HttpMethod.DELETE);
    }

    public <B, R> R jsonRequest(String baseUri, String resource
            , B body
            , ParameterizedTypeReference<R> typeReference
            , Map<String, String> urlParamMap
            , MultiValueMap<String, String> queryParamMap
            , HttpMethod httpMethod) throws RestApiCallError {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<B> request = new HttpEntity<>(body, headers);

        if(urlParamMap == null) {
            urlParamMap = new LinkedHashMap<>();
        }
        
        if(queryParamMap == null) {
            queryParamMap = new LinkedMultiValueMap<>();
        }
        
        URI uri = UriComponentsBuilder
                .fromUriString(baseUri + resource)
                .queryParams(queryParamMap)
                .buildAndExpand(urlParamMap)
                .encode()
                .toUri();

        ResponseEntity<R> httpResponseEntity = restTemplate.exchange(uri, httpMethod, request, typeReference);
        
        if(hasError(httpResponseEntity)){
            throw new RestApiCallError();
        }
        
        return httpResponseEntity.getBody();
    }
    
    protected abstract <R> boolean hasError(ResponseEntity<R>  resposneEntity);
}
