package vip.yazilim.libs.springvip.util


import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import vip.yazilim.libs.springvip.exception.RestApiCallError
import java.net.URI
import java.util.*

/**
 * To Make an HTTP request to a REST Service etc.
 *
 * @author Emre Sen (maemresen@yazilim.vip), 26.07.2019

 * @param baseUri the base URI that request will be mde
 * @param resource the resource requested by server
 * @param typeReference the response type of HTTP response will be mapped
 * @param urlParamMap URL parameters passed through URI (e.g. /api/car/{brand} => /api/car/BMW)
 * @param queryParamMap URL parameters passed as query params (e.g. /api/car?brand=BMW)
 * @param httpMethod type of HTTP request (GET, POST, etc.)
 * @param headerParamMap the extra fields that could be passed through header
 * @param restTemplate the Spring RestTemplate instance to make http requests
 * @return HTTP response returned by  endpoint
 */
@Throws(RestApiCallError::class)
fun <B, R> jsonRequest(baseUri: String, resource: String
                       , body: B?
                       , typeReference: ParameterizedTypeReference<R>
                       , urlParamMap: MutableMap<String?, String?> = LinkedHashMap()
                       , queryParamMap: MultiValueMap<String?, String?> = LinkedMultiValueMap()
                       , httpMethod: HttpMethod
                       , headerParamMap: MultiValueMap<String?, String?> = LinkedMultiValueMap()
                       , restTemplate: RestTemplate = RestTemplate()): ResponseEntity<R?> {
    return try {
        val headers = HttpHeaders()
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        headers.addAll(headerParamMap)
        val request: HttpEntity<B?> = HttpEntity(body, headers)
        val uri: URI = UriComponentsBuilder
                .fromUriString(baseUri + resource)
                .queryParams(queryParamMap)
                .buildAndExpand(urlParamMap)
                .encode()
                .toUri()
        restTemplate.exchange(uri, httpMethod, request, typeReference)
    } catch (e: RestClientException) {
        throw RestApiCallError(e)
    }
}
