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
class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @NotBlank(message = "name is a mandatory name")
    var name: String? = null

    @NotBlank(message = "age is a mandatory field")
    var age: Double? = null
    override fun toString(): String {
        return "Person(id=$id, name=$name, age=$age)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (id != other.id) return false
        if (name != other.name) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (age?.hashCode() ?: 0)
        return result
    }
}
