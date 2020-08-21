package vip.yazilim.libs.springvip.service

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*
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

abstract class AGenericServiceWriteImpl<E : Any, ID : Any>(
        repository: JpaRepository<E, ID>,
        classOfEntity: KClass<E>
) : AGenericServiceReadImpl  <E, ID>(repository, classOfEntity), IGenericServiceWrite<E, ID> {

    override fun save(entity: E): E {
        return super.saveGenericImpl(entity);
    }


    override fun create(entity: E): E {
        return super.createGenericImpl(entity);
    }

    override fun update(newEntity: E): E {
        return super.updateGenericImpl(newEntity);
    }
}

abstract class AGenericServiceReadImpl<E : Any, ID : Any>(
        repository: JpaRepository<E, ID>,
        classOfEntity: KClass<E>
) : GenericCrudMethod<E, ID>(repository, classOfEntity), IGenericServiceRead<E, ID> {

    override fun getAll(): List<E> {
        return super.getAllGenericImpl();
    }

    override fun getById(id: ID): Optional<E> {
        return super.getByIdGenericImpl(id);
    }
}
