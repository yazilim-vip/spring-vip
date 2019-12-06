package vip.yazilim.spring.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vip.yazilim.spring.core.exception.general.InvalidArgumentException;
import vip.yazilim.spring.core.exception.general.InvalidUpdateException;
import vip.yazilim.spring.core.exception.general.database.DatabaseCreateException;
import vip.yazilim.spring.core.exception.general.database.DatabaseDeleteException;
import vip.yazilim.spring.core.exception.general.database.DatabaseException;
import vip.yazilim.spring.core.exception.general.database.DatabaseReadException;
import vip.yazilim.spring.core.exception.general.database.DatabaseUpdateException;

/**
 * Abstract Implementation of ICrudService.
 * <p>
 * Basic CRUD operations for an entity is implemented.
 *
 * @param <E> type entity
 * @param <ID> type of identity of entity
 * @author Emre Sen, 19.07.2019
 * @contact maemresen@yazilim.vip
 */
public abstract class ACrudServiceImpl<E, ID> implements ICrudService<E, ID> {

    /**
     * Repository of the entity that will be used with this implementation
     *
     * @return repository
     */
    protected abstract JpaRepository<E, ID> getRepository();

    /**
     * Get Id of the entity
     *
     * @param entity input entity model
     * @return id of entity
     */
    protected abstract ID getId(E entity);

    @Override
    public E save(E entity) throws DatabaseException {
        JpaRepository<E, ID> repository = getRepository();

        // save entity
        return repository.save(entity);
    }

    @Override
    public E create(E entity) throws DatabaseException {

        // initialize entity to insert
        // e.g setting unique UUID
        E initializedEntity = preInsert(entity);

        try {
            return save(initializedEntity);
        } catch (Exception e) {
            throw new DatabaseCreateException(e);
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
    public E update(E newEntity) throws DatabaseException, InvalidUpdateException, InvalidArgumentException {
        // get old entity
        ID id = getId(newEntity);
        Optional<E> oldEntity = getById(id);

        if (!oldEntity.isPresent()) {
            throw new InvalidUpdateException("Cannot update non-exist enity");
        }

        // prepare entity for update
        E preparedEntity = preUpdate(oldEntity.get(), newEntity);

        try {
            return save(preparedEntity);
        } catch (Exception e) {
            throw new DatabaseUpdateException(e);
        }
    }

    @Override
    public List<E> getAll() throws DatabaseException {
        JpaRepository<E, ID> repository = getRepository();
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new DatabaseReadException(e);
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
            throw new DatabaseReadException(exception);
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
            throw new DatabaseDeleteException(exception);
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
            throw new DatabaseDeleteException(exception);
        }
    }

    @Override
    public boolean deleteAll() throws DatabaseException {
        try {
            JpaRepository<E, ID> repository = getRepository();

            // delete entity
            repository.deleteAll();
            return true;
        } catch (Exception e) {
            throw new DatabaseDeleteException(e);
        }
    }
}
