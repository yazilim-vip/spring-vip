package vip.yazilim.libs.springvip.config.bean

import org.springframework.stereotype.Component

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 17.10.2020
 */
@Component
interface IGenericRestLoader {
    fun loadGenericRestControllers(packageName: String)
}
