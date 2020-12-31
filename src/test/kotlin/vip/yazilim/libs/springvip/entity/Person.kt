package vip.yazilim.libs.springvip.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 12/31/2020
 */
@Entity
class Person(
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @NotBlank(message = "name is a mandatory name")
    var name: String? = null

    @NotBlank(message = "age is a mandatory field")
    var age: Double? = null

}
