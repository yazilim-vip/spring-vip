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

/**
 * Generic REST Connection to make REST API calls.
 * 
 * @author Emre Sen - Oct 19, 2019
 * @contact maemresen07@gmail.com
 *
 */
public abstract class ARestConnection {

	/**
	 * RestTemplate to make REST API call
	 * 
	 * @return rest template
	 */
	protected abstract RestTemplate getRestTemplate();

	/**
	 * To get endpoint of the REST API
	 * 
	 * @return
	 */
	protected abstract String getEndpoint();

	/**
	 * Helper method to make PUT request
	 * 
	 * @param <R>         type of response body
	 * @param <B>         type of request body
	 * @param endpoint    ...
	 * @param resource    ...
	 * @param requestBody body of post request
	 * @return response body
	 */
	protected <R, B> R deleteRequest(String resource, B requestBody) {
		return execute(resource, HttpMethod.DELETE, null, requestBody);
	}

	/**
	 * Helper method to make PUT request
	 * 
	 * @param <R>         type of response body
	 * @param <B>         type of request body
	 * @param endpoint    ...
	 * @param resource    ...
	 * @param requestBody body of post request
	 * @return response body
	 */
	protected <R, B> R putRequest(String resource, B requestBody) {
		return execute(resource, HttpMethod.PUT, null, requestBody);
	}

	/**
	 * Helper method to make POST request
	 * 
	 * @param <R>         type of response body
	 * @param <B>         type of request body
	 * @param endpoint    ...
	 * @param resource    ...
	 * @param requestBody body of post request
	 * @return response body
	 */
	protected <R, B> R postRequest(String resource, B requestBody) {
		return execute(resource, HttpMethod.POST, null, requestBody);
	}

	/**
	 * Helper method to make GET Request
	 * 
	 * @param <T>      type of response body
	 * @param endpoint ...
	 * @param resource ...
	 * @return response body
	 */
	protected <R, B> R getRequest(String resource) {
		return getRequest(resource, null);
	}

	/**
	 * Helper method to make GET request
	 * 
	 * @param <T>      type of response
	 * @param endpoint ...
	 * @param resource ...
	 * @param params   GET request parameters
	 * @return response body
	 */
	protected <R, B> R getRequest(String resource, MultiValueMap<String, String> params) {
		return execute(resource, HttpMethod.GET, params, null);
	}

	private <R, B> R execute(String resource, HttpMethod httpMethod, MultiValueMap<String, String> params,
			B requestBody) {
		String url = getEndpoint() + resource;
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		if (params != null) {
			builder = builder.queryParams(params);
		}

		// do http request
		RestTemplate restTemplate = getRestTemplate();
		HttpEntity<B> request = new HttpEntity<>(requestBody, getHttpHeaders());

		URI uri = builder.build().encode().toUri();

		ParameterizedTypeReference<R> parameterizedTypeReference = new ParameterizedTypeReference<R>() {
		};

		ResponseEntity<R> response = restTemplate.exchange(uri, httpMethod, request, parameterizedTypeReference);
		return response.getBody();
	}

	private MultiValueMap<String, String> getHttpHeaders() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		// Example: headers.add(HttpHeaders.AUTHORIZATION, "Basic " +
		// getBaseCredentials());
		return headers;
	}

}
