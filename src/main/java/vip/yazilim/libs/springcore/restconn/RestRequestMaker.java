package vip.yazilim.libs.springcore.restconn;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vip.yazilim.libs.springcore.util.JacksonHelper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Generic REST Connection to make REST API calls.
 *
 * @author Emre Sen - Oct 19, 2019
 * @contact maemresen@yazilim.vip
 */
public class RestRequestMaker {

    private final OkHttpClient okHttpClient;
    private final MediaType mediaTypeJson;

    private final String endpoint;

    public RestRequestMaker(String endpoint) {
        okHttpClient = new OkHttpClient();
        mediaTypeJson = MediaType.get("application/json; charset=utf-8");
        this.endpoint = endpoint;
    }

    /**
     * Helper method to make PUT request
     * put
     *
     * @param <R>      type of response body
     * @param <B>      type of request body
     * @param resource ...
     * @return response body
     * @throws JsonProcessingException
     */
    public <R, B> R deleteRequest(String resource, TypeReference<R> responseTypeReference) throws IOException {
        String url = endpoint + resource;
        Request request = new Request.Builder().url(url).delete().build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            String responseJson = response.body().string();
            return mapJsonToObject(responseJson, responseTypeReference);
        }
    }

    /**
     * Helper method to make PUT request
     *
     * @param <R>      type of response body
     * @param <B>      type of request body
     * @param resource ...
     * @return response body
     */
    public <R, B> R putRequest(String resource, B b, TypeReference<R> responseTypeReference) throws IOException {

        String bodyJson = JacksonHelper.toJson(b);
        RequestBody body = RequestBody.create(mediaTypeJson, bodyJson);
        String url = endpoint + resource;
        Request request = new Request.Builder().url(url).put(body).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            String responseJson = response.body().string();
            return mapJsonToObject(responseJson, responseTypeReference);
        }

    }

    /**
     * Helper method to make POST request
     *
     * @param <R>      type of response body
     * @param <B>      type of request body
     * @param resource ...
     * @return response body
     * @throws IOException
     */
    public <R, B> R postRequest(String resource, B b, TypeReference<R> responseTypeReference) throws IOException {

        String bodyJson = JacksonHelper.toJson(b);
        RequestBody body = RequestBody.create(mediaTypeJson, bodyJson);
        String url = endpoint + resource;
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            String responseJson = response.body().string();
            return mapJsonToObject(responseJson, responseTypeReference);
        }

    }

    /**
     * Helper method to make GET Request
     *
     * @param resource ...
     * @return response body
     * @throws IOException
     */
    public <R, B> R getRequest(String resource, TypeReference<R> responseType) throws IOException {

        String url = endpoint + resource;
        Request request = new Request.Builder().url(url).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            String responseBody = response.body().string();
            return mapJsonToObject(responseBody, responseType);
        }
    }

    protected <R> R mapJsonToObject(String responseJson, TypeReference<R> responseType)
            throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseJson, responseType);
    }


}
