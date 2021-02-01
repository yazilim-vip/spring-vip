package vip.yazilim.libs.springvip.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vip.yazilim.libs.springvip.config.bean.IRestResponseBuilder
import vip.yazilim.libs.springvip.entity.Person
import vip.yazilim.libs.springvip.service.IPersonService
import vip.yazilim.libs.springvip.util.generic.rest.AGenericRest
import vip.yazilim.libs.springvip.util.generic.rest.GenericRest
import vip.yazilim.libs.springvip.util.generic.rest.method.*

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 1/1/2021
 */
@RestController
@RequestMapping("/person")
@GenericRest
@GetAll
class RestPersonGetAll(
    private val restResponseBuilder: IRestResponseBuilder,
    private val personService: IPersonService
) : AGenericRest<Person, Long>(
    restResponseBuilder,
    personService,
    Person::class,
    Long::class
)