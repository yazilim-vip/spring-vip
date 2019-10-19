package cloud.cantek.ms.core.restconn;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AccessLevel;
import lombok.Setter;

/**
 * Thanks to, mehmetkarakoc
 * 
 * @author Emre Sen - Oct 19, 2019
 * @contact maemresen07@gmail.com
 *
 * @param <R> type of response body
 * @param <B> type of request body
 */
@Setter(value = AccessLevel.PACKAGE)
public class JsonRestRequest<R, B> {

	private final ParameterizedTypeReference<R> parameterizedTypeReference = new ParameterizedTypeReference<R>() {
	};

	private String endpoint;
	private String resource;
	private HttpMethod httpMethod;
	private MultiValueMap<String, String> params;
	private B requestBody;
	
	JsonRestRequest() {
	}
	
	public R execute() {
		String url = endpoint + resource;
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		if (params != null) {
			builder = builder.queryParams(params);
		}

		// do get request
		RestTemplate template = new RestTemplate();
		HttpEntity<B> request = new HttpEntity<>(requestBody, getHttpHeaders());

		URI uri = builder.build().encode().toUri();

		ParameterizedTypeReference<R> parameterizedTypeReference = new ParameterizedTypeReference<R>() {
		};

		ResponseEntity<R> response = template.exchange(uri, httpMethod, request, parameterizedTypeReference);
		return response.getBody();
	}

	private final static MultiValueMap<String, String> getHttpHeaders() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		// Example: headers.add(HttpHeaders.AUTHORIZATION, "Basic " +
		// getBaseCredentials());
		return headers;
	}
}
