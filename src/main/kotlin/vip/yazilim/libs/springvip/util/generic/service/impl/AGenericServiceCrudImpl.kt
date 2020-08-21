package vip.yazilim.libs.springvip.util.generic.service.impl

import org.springframework.data.jpa.repository.JpaRepository
import vip.yazilim.libs.springvip.util.generic.service.IGenericServiceCrud
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
abstract class AGenericServiceCrudImpl<E : Any, ID : Any>(
        repository: JpaRepository<E, ID>,
        classOfEntity: KClass<E>
) : AGenericServiceWriteImpl<E, ID>(repository, classOfEntity), IGenericServiceCrud<E, ID> {

    override fun deleteById(id: ID): Boolean {
        return super.deleteByIdGenericImpl(id);
    }

    override fun delete(entity: E): Boolean {
        return super.deleteGenericImpl(entity);
    }

    override fun deleteAll(): Boolean {
        return super.deleteAllGenericImpl();
    }
}