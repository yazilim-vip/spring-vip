package vip.yazilim.libs.springvip.util.generic.rest

import org.springframework.web.bind.annotation.RequestMethod

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 17.10.2020
 */
enum class GenericCrudMethods(val httpMethod: RequestMethod, val methodName: String, val uri: String) {
    CREATE(RequestMethod.POST, "createGenericImpl", "/"),
    DELETE_ALL(RequestMethod.DELETE, "deleteAllGenericImpl", "/"),
    DELETE_BY_ID(RequestMethod.DELETE, "deleteByIdGenericImpl", "/"),
    DELETE(RequestMethod.DELETE, "deleteGenericImpl", "/"),
    GET_ALL(RequestMethod.GET, "getAllGenericImpl", "/"),
    GET_BY_ID(RequestMethod.GET, "getByIdGenericImpl", "/"),
    SAVE(RequestMethod.POST, "saveGenericImpl", "/"),
    UPDATE(RequestMethod.PUT, "updateGenericImpl", "/"),
}