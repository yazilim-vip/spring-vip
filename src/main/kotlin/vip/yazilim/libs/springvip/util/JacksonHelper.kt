package vip.yazilim.libs.springvip.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Convert object to JSON
 *
 * @param `object` object that will be converted to JSON
 * @param indented  if true, output JSON will be prettified
 * @return Object JSON String
 */
fun toJson(`object`: Any?, indented: Boolean): String {
    if (`object` == null) {
        return ""
    }
    if (indented) {
        return toJson(`object`)
    }
    val mapper = ObjectMapper()
    return try {
        mapper.writeValueAsString(`object`)
    } catch (e: JsonProcessingException) {
        `object`.toString()
    }
}

fun toJson(`object`: Any?): String {
    if (`object` == null) {
        return ""
    }
    val mapper = ObjectMapper()
    return try {
        mapper.writerWithDefaultPrettyPrinter().writeValueAsString(`object`)
    } catch (e: JsonProcessingException) {
        `object`.toString()
    }
}

/**
 * Map object from JSON
 * @param json input that Object will be parsed from
 * @param typeReference type of the Object that will be mapped from input JSON
 * @return mapped Object instance
 */
fun <T> fromJson(json: String, typeReference: TypeReference<T>): T {
    return ObjectMapper().readValue(json, typeReference)
}
