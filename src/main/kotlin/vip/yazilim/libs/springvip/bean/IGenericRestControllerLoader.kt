package vip.yazilim.libs.springvip.bean

import org.springframework.stereotype.Component

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 17.10.2020
 */
@Component
interface IGenericRestControllerLoader {
    fun loadGenericRestControllers(packageName: String)
}
