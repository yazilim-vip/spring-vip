package cloud.cantek.ms.octocloud.core.util.request;

import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

public abstract class ARestConnection {

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
	protected <R, B> JsonRestRequest<R, B> deleteRequest(String resource, B requestBody) {
		JsonRestRequest<R, B> jsonRestRequest = new JsonRestRequest<R, B>();
		jsonRestRequest.setEndpoint(getEndpoint());
		jsonRestRequest.setResource(resource);
		jsonRestRequest.setHttpMethod(HttpMethod.DELETE);
		jsonRestRequest.setRequestBody(requestBody);
		return jsonRestRequest;
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
	protected <R, B> JsonRestRequest<R, B> putRequest(String resource, B requestBody) {
		JsonRestRequest<R, B> jsonRestRequest = new JsonRestRequest<R, B>();
		jsonRestRequest.setEndpoint(getEndpoint());
		jsonRestRequest.setResource(resource);
		jsonRestRequest.setHttpMethod(HttpMethod.PUT);
		jsonRestRequest.setRequestBody(requestBody);
		return jsonRestRequest;
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
	protected <R, B> JsonRestRequest<R, B> postRequest(String resource, B requestBody) {
		JsonRestRequest<R, B> jsonRestRequest = new JsonRestRequest<R, B>();
		jsonRestRequest.setEndpoint(getEndpoint());
		jsonRestRequest.setResource(resource);
		jsonRestRequest.setHttpMethod(HttpMethod.POST);
		jsonRestRequest.setRequestBody(requestBody);
		return jsonRestRequest;
	}

	/**
	 * Helper method to make GET Request
	 * 
	 * @param <T>      type of response body
	 * @param endpoint ...
	 * @param resource ...
	 * @return response body
	 */
	protected <R, B> JsonRestRequest<R, B> getRequest(String resource) {
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
	protected <R, B> JsonRestRequest<R, B> getRequest(String resource, MultiValueMap<String, String> params) {
		JsonRestRequest<R, B> jsonRestRequest = new JsonRestRequest<>();
		jsonRestRequest.setEndpoint(getEndpoint());
		jsonRestRequest.setResource(resource);
		jsonRestRequest.setHttpMethod(HttpMethod.GET);
		return jsonRestRequest;
	}

}
