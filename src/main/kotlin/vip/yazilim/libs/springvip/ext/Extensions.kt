package vip.yazilim.libs.springvip.ext

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import vip.yazilim.libs.springvip.util.buildLogMsg

/**
 * @author mustafaarifsisman - 27.02.2020
 * @contact mustafaarifsisman@gmail.com
 */

inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}
