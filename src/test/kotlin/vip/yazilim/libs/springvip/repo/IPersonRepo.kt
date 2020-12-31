package vip.yazilim.libs.springvip.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import vip.yazilim.libs.springvip.entity.Person

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 12/31/2020
 */
@Repository
interface IPersonRepo : JpaRepository<Person, Long>