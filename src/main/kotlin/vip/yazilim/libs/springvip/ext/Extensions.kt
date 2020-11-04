package vip.yazilim.libs.springvip.ext

import org.springframework.core.ParameterizedTypeReference

/**
 * @author mustafaarifsisman - 27.02.2020
 * @contact mustafaarifsisman@gmail.com
 */

inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}
