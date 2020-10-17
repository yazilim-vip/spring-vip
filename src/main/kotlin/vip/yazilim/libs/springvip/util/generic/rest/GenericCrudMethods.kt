package vip.yazilim.libs.springvip.util.generic.rest

import org.springframework.web.bind.annotation.RequestMethod

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 17.10.2020
 */
enum class GenericCrudMethods(val httpMethod: RequestMethod, val methodName: String, val uri: String) {

    DELETE_ALL(RequestMethod.DELETE, "deleteAllGenericImpl", "/"),
    GET_ALL(RequestMethod.GET, "getAllGenericImpl", "/"),

    SAVE(RequestMethod.POST, "saveGenericImpl", "/"),
    CREATE(RequestMethod.POST, "createGenericImpl", "/"),
    UPDATE(RequestMethod.PUT, "updateGenericImpl", "/"),
    DELETE(RequestMethod.DELETE, "deleteGenericImpl", "/"),

    DELETE_BY_ID(RequestMethod.DELETE, "deleteByIdGenericImpl", "/"),
    GET_BY_ID(RequestMethod.GET, "getByIdGenericImpl", "/{id}"),
}