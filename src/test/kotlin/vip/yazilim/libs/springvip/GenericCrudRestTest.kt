package vip.yazilim.libs.springvip

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import vip.yazilim.libs.springvip.entity.Person
import vip.yazilim.libs.springvip.service.IPersonService


/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 1/1/2021
 */

@ExtendWith(SpringExtension::class)
@WebMvcTest(
    properties = [
        "spring-vip.rest-packages=vip.yazilim.libs.springvip"
    ], controllers = []
)
class GenericCrudRestTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var personService: IPersonService

    @Test
    fun firstTest() {
        Mockito.`when`(personService.getAll()).thenReturn(
            listOf(
                createMockPerson(1, "emre", 23.0)
            )
        )
        this.mockMvc.perform(MockMvcRequestBuilders.get("/person/"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    private fun createMockPerson(id: Long, name: String, age: Double): Person {
        val person = Person()
        person.id = id
        person.name = name
        person.age = age
        return person
    }
}

