package vip.yazilim.libs.springvip.rest.model

import lombok.Data

/**
 * @author Emre Sen, 24.07.2019
 * @contact maemresen@yazilim.vip
 */
@Data
class RestError<C>(private val errorCode: Int, private val errorCause: C) 