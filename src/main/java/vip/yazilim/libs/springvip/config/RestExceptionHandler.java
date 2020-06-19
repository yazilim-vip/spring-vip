package vip.yazilim.libs.springvip.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vip.yazilim.libs.springvip.rest.model.RestError;
import vip.yazilim.libs.springvip.rest.model.RestErrorResponse;

import java.util.*;

/**
 * @author Emre Sen, 19.06.2020
 * @contact maemresen@yazilim.vip
 */
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
            , HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Map<String, Object>> errorSummaryList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            Map<String, Object> errorMap = new LinkedHashMap<>();

            errorMap.put("errorCode", error.getCode());
            errorMap.put("objectName", error.getObjectName());
            errorMap.put("defaultMessage", error.getDefaultMessage());
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorMap.put("field", fieldError.getField());
                errorMap.put("rejectedValue", fieldError.getRejectedValue());
            }
            errorSummaryList.add(errorMap);
        });

        return handleResponse(request, HttpStatus.BAD_REQUEST, ex, errorSummaryList);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleResponse(request, HttpStatus.BAD_REQUEST, ex, "Invalid JSON in Body");
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleResponse(request, status, ex);
    }

    protected final <R> ResponseEntity<Object> handleResponse(WebRequest request
            , HttpStatus httpStatus
            , Exception ex) {
        return handleResponse(request, httpStatus, ex, null);
    }

    protected final ResponseEntity<Object> handleResponse(WebRequest request
            , HttpStatus httpStatus
            , Exception ex
            , Object reason) {

        // get requested pathexception.getMessage()
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();


        Map<String, Object> reasonMap = new TreeMap<>();
        if (reason != null) {
            reasonMap.put("reason", reason);
        }

        List<Map<String, Object>> exceptionChainList = new ArrayList<>();
        Throwable currEx = ex;
        int depth = 1;
        while (currEx != null) {
            Class<?> causeClass = currEx.getClass();
            String causeMessage = currEx.getMessage();
            Map<String, Object> treeMap = new TreeMap<>();
            treeMap.put("depth", String.valueOf(depth++));
            treeMap.put("class", causeClass.toString());
            treeMap.put("message", causeMessage);
//            treeMap.put("stackTrace", currEx.getStackTrace());
            exceptionChainList.add(treeMap);
            currEx = currEx.getCause();
        }
        reasonMap.put("chain", exceptionChainList);

        // init error model
        RestErrorResponse restResponse = new RestErrorResponse();
        restResponse.setTimestamp(new Date().getTime());
        restResponse.setPath(path);
        restResponse.setMessage(httpStatus.getReasonPhrase());
        restResponse.setData(new RestError<>(1001, reasonMap));
        return new ResponseEntity<>(restResponse, httpStatus);
    }
}