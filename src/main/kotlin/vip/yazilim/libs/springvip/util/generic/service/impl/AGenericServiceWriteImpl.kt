package vip.yazilim.libs.springvip.util.generic.service.impl

import org.springframework.data.jpa.repository.JpaRepository
import vip.yazilim.libs.springvip.util.generic.service.IGenericServiceWrite
import kotlin.reflect.KClass

/**
 * Abstract Implementation of ICrudService.
 *
 *
 * Basic CRUD operations for an entity is implemented.
 *
 * @param <E>  type entity
 * @param <ID> type of identity of entity
 * @author Emre Sen, 19.07.2019
 * @contact maemresen@yazilim.vip
</ID></E> */
abstract class AGenericServiceWriteImpl<E : Any, ID : Any>(
        repository: JpaRepository<E, ID>,
        classOfEntity: KClass<E>
) : AGenericServiceReadImpl<E, ID>(repository, classOfEntity), IGenericServiceWrite<E, ID> {

}
