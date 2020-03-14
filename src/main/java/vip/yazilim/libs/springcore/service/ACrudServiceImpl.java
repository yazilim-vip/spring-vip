package vip.yazilim.libs.springcore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vip.yazilim.libs.springcore.exception.checked.BusinessLogicException;
import vip.yazilim.libs.springcore.exception.checked.InvalidArgumentException;
import vip.yazilim.libs.springcore.exception.checked.database.DatabaseCreateException;
import vip.yazilim.libs.springcore.exception.checked.database.DatabaseDeleteException;
import vip.yazilim.libs.springcore.exception.checked.database.DatabaseException;
import vip.yazilim.libs.springcore.exception.checked.database.DatabaseReadException;
import vip.yazilim.libs.springcore.exception.checked.database.DatabaseSaveException;
import vip.yazilim.libs.springcore.exception.checked.database.DatabaseUpdateException;

/**
 * Abstract Implementation of ICrudService.
 * <p>
 * Basic CRUD operations for an entity is implemented.
 *
 * @param <E>  type entity
 * @param <ID> type of identity of entity
 * @author Emre Sen, 19.07.2019
 * @contact maemresen@yazilim.vip
 */
public abstract class ACrudServiceImpl<E, ID> implements ICrudService<E, ID> {

    /**
     * Repository of the entity that will be used with this implementation
     */
    protected abstract JpaRepository<E, ID> getRepository();

    protected abstract Class<E> getClassOfEntity();
    
    /**
     * Get Id of the entity
     *
     * @param entity input entity model
     * @return id of entity
     */
    protected abstract ID getId(E entity);


    public E save(E entity) throws Exception {
        JpaRepository<E, ID> repository = this.getRepository();

        E savedEntity;
        try {
            savedEntity = repository.save(entity);
        } catch (Exception exception) {
            throw new DatabaseSaveException(entity.getClass(), this.getId(entity), exception);
        }

        if (savedEntity == null) {
            throw new BusinessLogicException("Created entity not found");
        } else {
            return savedEntity;
        }
    }

    @Override
    public E create(E entity) throws DatabaseException {

        // initialize entity to insert
        // e.g setting unique UUID
        E initializedEntity = preInsert(entity);

        try {
            return save(initializedEntity);
        } catch (Exception exception) {
        	throw new DatabaseCreateException(getClassOfEntity(), this.getId(initializedEntity), exception);
        }
    }

    /**
     * Operations before insert entity to the table
     * <p>
     * E.g.: setting uuid value
     *
     * @param entity values that will be inserted
     * @return entity that inserted to the data source
     */
    protected E preInsert(E entity) {
        return entity;
    }

    @Override
    public E update(E newEntity) throws DatabaseException, InvalidArgumentException {
        // get old entity
        ID id = getId(newEntity);
        Optional<E> oldEntity = getById(id);

        if (!oldEntity.isPresent()) {
            throw new InvalidArgumentException("Cannot update non-exist enity");
        }

        // prepare entity for update
        E preparedEntity = preUpdate(oldEntity.get(), newEntity);

        try {
            return save(preparedEntity);
        } catch (Exception exception) {
        	throw new DatabaseUpdateException(getClassOfEntity(), id, exception);
        }
    }

    @Override
    public List<E> getAll() throws DatabaseException {
        JpaRepository<E, ID> repository = getRepository();
        try {
            return repository.findAll();
        } catch (Exception exception) {
        	throw new DatabaseReadException(getClassOfEntity(), exception);
        }
    }

    @Override
    public Optional<E> getById(ID id) throws DatabaseException, InvalidArgumentException {
        if (id == null) {
            throw new InvalidArgumentException("ID cannot be null");
        }
        JpaRepository<E, ID> repository = getRepository();
        try {
            // find entity by id
            return repository.findById(id);
        } catch (Exception exception) {
        	throw new DatabaseReadException(getClassOfEntity(), exception);
        }
    }

    /**
     * Operations before update operation
     *
     * @param oldEntity old data on data source
     * @param newEntity new data that will be saved to data source
     * @return prepared entity to update
     */
    protected E preUpdate(E oldEntity, E newEntity) {
        return newEntity;
    }

    @Override
    public boolean deleteById(ID id) throws DatabaseException {
        try {
            JpaRepository<E, ID> repository = getRepository();

            // delete entity
            repository.deleteById(id);
            return true;
        } catch (Exception exception) {
        	throw new DatabaseDeleteException(getClassOfEntity(), exception);
        }
    }

    @Override
    public boolean delete(E entity) throws DatabaseException {
        try {
            JpaRepository<E, ID> repository = getRepository();

            // delete entity
            repository.delete(entity);
            return true;
        } catch (Exception exception) {
        	throw new DatabaseDeleteException(getClassOfEntity(), exception);
        }
    }


    @Override
    public boolean deleteAll() throws DatabaseException {
        try {
            JpaRepository<E, ID> repository = getRepository();

            // delete entity
            repository.deleteAll();
            return true;
        } catch (Exception exception) {
        	throw new DatabaseDeleteException(getClassOfEntity(), exception);
        }
    }
}
