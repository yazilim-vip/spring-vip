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
 * @author Emre Sen, 24.06.2020
 * @contact yazilim.vip
 */
/**
 * @author Emre Sen, 26.07.2019
 * @contact maemresen@yazilim.vip
 */
class HttpRequestMaker {

    @Throws(RestApiCallError::class)
    fun <B, R> jsonRequest(baseUri: String, resource: String
                           , body: B?
                           , typeReference: ParameterizedTypeReference<R>
                           , urlParamMap: MutableMap<String?, String?> = LinkedHashMap()
                           , queryParamMap: MultiValueMap<String?, String?> = LinkedMultiValueMap()
                           , httpMethod: HttpMethod
                           , restTemplate: RestTemplate = RestTemplate()): ResponseEntity<R?> {
        return try {
            val headers = HttpHeaders()
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
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
}
