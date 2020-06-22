package vip.yazilim.libs.springvip.util

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import vip.yazilim.libs.springvip.exception.RestApiCallError
import java.util.*
import javax.print.DocFlavor
import kotlin.collections.LinkedHashMap

/**
 * @author Emre Sen, 26.07.2019
 * @contact maemresen@yazilim.vip
 */
class HttpRequestMaker {
    @Throws(RestApiCallError::class)
    fun <B, R> jsonRequest(baseUri: String, resource: String
                           , body: B
                           , typeReference: ParameterizedTypeReference<R>
                           , urlParamMap: Map<String?, String?>?
                           , queryParamMap: MultiValueMap<String?, String?>?
                           , httpMethod: HttpMethod): ResponseEntity<R> {
        return jsonRequest(baseUri
                , resource
                , body
                , typeReference
                , urlParamMap
                , queryParamMap
                , httpMethod
                , RestTemplate())
    }

    @Throws(RestApiCallError::class)
    fun <B, R> jsonRequest(baseUri: String, resource: String
                           , body: B
                           , typeReference: ParameterizedTypeReference<R>
                           , urlParamMap: Map<String?, String?>?
                           , queryParamMap: MultiValueMap<String?, String?>?
                           , httpMethod: HttpMethod
                           , restTemplate: RestTemplate): ResponseEntity<R> {
        return try {
            val headers = HttpHeaders()
            headers[HttpHeaders.ACCEPT] = MediaType.APPLICATION_JSON_VALUE

            val request = HttpEntity(body, headers)
            val uri = UriComponentsBuilder
                    .fromUriString(baseUri + resource)
                    .queryParams(queryParamMap ?: LinkedMultiValueMap<String, String>())
                    .buildAndExpand(urlParamMap ?: emptyMap<String, String>())
                    .encode()
                    .toUri()

            restTemplate.exchange(uri, httpMethod, request, typeReference)
        } catch (e: RestClientException) {
            throw RestApiCallError(e)
        }
    }
}