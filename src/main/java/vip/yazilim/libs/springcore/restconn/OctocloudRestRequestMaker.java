package vip.yazilim.libs.springcore.restconn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Emre Sen - Feb 19, 2020
 * @contact maemresen@yazilim.vip
 */
public class OctocloudRestRequestMaker extends RestRequestMaker {

    public OctocloudRestRequestMaker(String endpoint) {
        super(endpoint);
    }

    @Override
    protected <R> R mapJsonToObject(String responseJson, TypeReference<R> responseType)
            throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseJson, responseType);
    }


}
