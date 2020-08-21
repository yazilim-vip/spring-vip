package vip.yazilim.libs.springvip.util.generic.service.impl

import org.springframework.data.jpa.repository.JpaRepository
import vip.yazilim.libs.springvip.util.generic.service.IGenericServiceRead
import java.util.*
import kotlin.reflect.KClass

/**
 * Abstract Implementation of ICrudService.
 *
 *
 * Basic Read operations for an entity is implemented.
 *
 * @param <E>  type entity
 * @param <ID> type of identity of entity
 * @author Emre Sen, 19.07.2019
 * @contact maemresen@yazilim.vip
</ID></E> */
abstract class AGenericServiceReadImpl<E : Any, ID : Any>(
        repository: JpaRepository<E, ID>,
        classOfEntity: KClass<E>
) : AGenericService<E, ID>(repository, classOfEntity), IGenericServiceRead<E, ID> {

    override fun getAll(): List<E> {
        return super.getAllGenericImpl();
    }

    override fun getById(id: ID): Optional<E> {
        return super.getByIdGenericImpl(id);
    }
}
