package vip.yazilim.libs.springvip

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import vip.yazilim.libs.springvip.entity.Person
import vip.yazilim.libs.springvip.repo.IPersonRepo
import vip.yazilim.libs.springvip.service.IPersonService
import kotlin.test.assertEquals

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

    private val mockPersonList = listOf(
        createMockPerson(1, "mehmet", 23.0),
        createMockPerson(2, "arif", 24.0),
        createMockPerson(3, "emre", 25.0)
    )


    @Test
    fun getAllTest() {
        Mockito.`when`(personRepo.findAll()).then { mockPersonList }
        val personList = personService.getAll()

        println("personList=${personList}")
        assertEquals(mockPersonList[0], personList[0])
        assertEquals(mockPersonList[1], personList[1])
        assertEquals(mockPersonList[2], personList[2])
    }


    @Test
    fun getByIdTest() {

    }

    @Test
    fun create() {

    }

    @Test
    fun update() {

    }

    @Test
    fun delete() {

    }

    @Test
    fun deleteById() {

    }

    private fun createMockPerson(id: Long, name: String, age: Double): Person {
        val person = Person()
        person.id = id
        person.name = name
        person.age = age
        return person
    }
}

