package vip.yazilim.libs.springvip.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kotlin.random.Random

/**
 * Helper methods to use Jackson library
 *
 * @author Emre Sen - Dec 26, 2019
 * @contact maemresen@yazilim.vip
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

@Throws(JsonProcessingException::class)
fun <T> fromJson(json: String?, typeReference: TypeReference<T>?): T {
    return ObjectMapper().readValue(json, typeReference)
}