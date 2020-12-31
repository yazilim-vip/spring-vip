package vip.yazilim.libs.springvip

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import vip.yazilim.libs.springvip.constants.BASE_PACKAGE

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 1/1/2021
 */
@SpringBootApplication(scanBasePackages = [BASE_PACKAGE])
open class TestApplication

fun main(args: Array<String>) {
    runApplication<TestApplication>(*args)
}
