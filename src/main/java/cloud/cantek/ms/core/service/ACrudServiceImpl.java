package cloud.cantek.ms.core.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import cloud.cantek.ms.core.constant.OctocloudMsCoreConstants;
import cloud.cantek.ms.core.exception.NotFoundException;
import cloud.cantek.ms.core.exception.ServiceException;

/**
 * Abstract Implementation of ICrudService.
 * <p>
 * Basic CRUD operations for an entity is implemented.
 *
 * @param <E>  type entity
 * @param <ID> type of identity of entity
 * @author Emre Sen, 19.07.2019
 * @contact maemresen07@gmail.com
 */
public abstract class ACrudServiceImpl<E, ID> implements ICrudService<E, ID> {

    private final Logger LOGGER = LoggerFactory.getLogger(ACrudServiceImpl.class);

    private final JpaRepository<E, ID> repository;

    public ACrudServiceImpl() {
        this.repository = getRepository();
    }

    /**
     * Repository of the entity that will be used with this implementation
     */
    public abstract JpaRepository<E, ID> getRepository();

    /**
     * Get Id of the entity
     *
     * @param entity input entity model
     * @return id of entity
     */
    protected abstract ID getId(E entity);


    public E save(E entity) throws ServiceException {
       try {
            // save entity
            return repository.save(entity);
        } catch (Exception exception) {
            throw new ServiceException(OctocloudMsCoreConstants.ERROR_MESSAGE_ENTITY_SAVE, exception);
        }
    }

    @Override
    public E create(E entity) throws ServiceException {
        ID id = getId(entity);
        boolean eventExists = repository.existsById(id);
        if (eventExists) {
            throw new ServiceException(OctocloudMsCoreConstants.ERROR_MESSAGE_ENTITY_SAVE);
        }

        boolean violateAnyConstraint = isViolatesAnyConstraint(entity);
        if (violateAnyConstraint) {
            throw new ServiceException("Entity Violates Constraints");
        }

        // initialize entity to insert
        // e.g setting unique UUID
        E initializedEntity = preInsert(entity);

        return save(initializedEntity);
    }

    /**
     * To check if entity violates any constraint
     * <p>
     * E.g.: some fields should be unique. That conditions should be checked.
     *
     * @param entity values that will be inserted
     * @return violate any constraint
     */
    protected boolean isViolatesAnyConstraint(E entity) {
        return false;
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
    public List<E> getAll() throws ServiceException {
        try {
            return repository.findAll();
        } catch (Exception exception) {
            throw new ServiceException(OctocloudMsCoreConstants.ERROR_MESSAGE_ENTITY_GET_LIST, exception);
        }
    }

    @Override
    public E getById(ID id) throws ServiceException, NotFoundException {
        // get repo
        Optional<E> entityOptional;

        try {
            // find entity by id
            entityOptional = repository.findById(id);
        } catch (Exception exception) {
            throw new ServiceException(OctocloudMsCoreConstants.ERROR_MESSAGE_ENTITY_GET_BY_ID, exception);
        }

        // get entity or throw exception if empty
        if (entityOptional.isPresent()) {

            return entityOptional.get();
        }

        LOGGER.error(OctocloudMsCoreConstants.ERROR_MESSAGE_ENTITY_NOT_FOUND);
        throw new NotFoundException(OctocloudMsCoreConstants.ERROR_MESSAGE_ENTITY_NOT_FOUND);
    }

    @Override
    public E update(E newEntity) throws ServiceException {
        // get old entity
        ID id = getId(newEntity);
        E oldEntity = getById(id);

        // prepare entity for update
        E preparedEntity = preUpdate(oldEntity, newEntity);

        return save(preparedEntity);
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
    public boolean delete(E entity) {
        try {
            // delete entity
            repository.delete(entity);
            return true;
        } catch (Exception exception) {
            throw new ServiceException(OctocloudMsCoreConstants.ERROR_MESSAGE_ENTITY_DELETE, exception);
        }
    }

    @Override
    public boolean deleteAll() {
        try {
            // delete entity
            repository.deleteAll();
            return true;
        } catch (Exception exception) {
            throw new ServiceException(OctocloudMsCoreConstants.ERROR_MESSAGE_ENTITY_DELETE, exception);
        }
    }
}
