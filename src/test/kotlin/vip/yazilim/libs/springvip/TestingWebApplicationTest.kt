package vip.yazilim.libs.springvip

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import vip.yazilim.libs.springvip.repo.IPersonRepo
import vip.yazilim.libs.springvip.service.IPersonService

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 1/1/2021
 */

@ExtendWith(SpringExtension::class)
@SpringBootTest(
    properties = [
        "spring-vip.rest-packages=vip.yazilim.libs.springvip"
    ]
)
class TestingWebApplicationTest {

    @MockBean
    private lateinit var personRepo: IPersonRepo

    @Autowired
    private lateinit var personService: IPersonService

    @Test
    fun contextLoads() {
        println(personService)
    }
}

