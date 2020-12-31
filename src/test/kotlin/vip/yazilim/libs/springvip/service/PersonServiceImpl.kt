package vip.yazilim.libs.springvip.service

import vip.yazilim.libs.springvip.entity.Person
import vip.yazilim.libs.springvip.repo.IPersonRepo
import vip.yazilim.libs.springvip.service.IPersonService
import vip.yazilim.libs.springvip.util.generic.service.impl.AGenericServiceCrud

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 12/31/2020
 */
class PersonServiceImpl(private val personRepo: IPersonRepo) :
    AGenericServiceCrud<Person, Long>(personRepo, Person::class, Long::class), IPersonService {
    override fun getId(entity: Person): Long {
        return entity.id!!
    }
}