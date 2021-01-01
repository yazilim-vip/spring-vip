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
import vip.yazilim.libs.springvip.util.generic.service.exception.DatabaseUpdateException
import java.util.*
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import vip.yazilim.libs.springvip.ext.libLogError
import vip.yazilim.libs.springvip.ext.libLogInfo
import vip.yazilim.libs.springvip.util.generic.service.exception.DatabaseCreateException


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
class GenericCrudServiceTest {

    @MockBean
    private lateinit var personRepo: IPersonRepo

    @Autowired
    private lateinit var personService: IPersonService

    @Test
    fun createAlreadyExistingEntityTest() {
        val assertLogger = AssertLogger("createAlreadyExistingEntityTest")

        val createPersonId = 1L
        val mockPersonExists = createMockPerson(createPersonId, "emre", 24.0)
        val mockPersonToCreate = createMockPerson(createPersonId, "arif", 23.0)
        Mockito.`when`(personRepo.findById(createPersonId)).then { Optional.of(mockPersonExists) }
        assertLogger.assert("assert DatabaseCreateException") {
            assertThrows(DatabaseCreateException::class.java) {
                personService.create(mockPersonToCreate)
            }
        }

    }

    @Test
    fun updateNonExistingEntityTest() {
        val assertLogger = AssertLogger("updateNonExistingEntityTest")

        val updatePersonId = 1L
        val mockPersonAfterUpdate = createMockPerson(updatePersonId, "arif", 24.0)
        Mockito.`when`(personRepo.findById(updatePersonId)).then { Optional.empty<Person>() }
        assertLogger.assert("assert DatabaseUpdateException") {
            assertThrows(DatabaseUpdateException::class.java) {
                personService.update(mockPersonAfterUpdate)
            }
        }
    }

    // Helpers
    inner class AssertLogger(private val testName: String) {
        fun assert(description: String, runnable: Runnable) {
            try {
                runnable.run()
                libLogInfo("$testName::$description - passed")
            } catch (e: AssertionError) {
                libLogError("$testName::$description - failed")
                throw e
            }
        }
    }

    private fun createMockPerson(id: Long, name: String, age: Double): Person {
        val person = Person()
        person.id = id
        person.name = name
        person.age = age
        return person
    }
}

