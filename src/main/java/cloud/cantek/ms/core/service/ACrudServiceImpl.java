package cloud.cantek.ms.core.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import cloud.cantek.ms.core.constant.NotifierMicroserviceConstants;
import cloud.cantek.ms.core.exception.NotFoundException;
import cloud.cantek.ms.core.exception.ServiceException;

/**
 * Generic Entity CRUD Service
 *
 * @param <E> type entity
 * @param <ID> type of identity of entity
 *
 * @author Emre Sen, 19.07.2019
 * @contact maemresen07@gmail.com
 *
 */
public abstract class ACrudServiceImpl<E, ID> implements ICrudService<E, ID> {

    private final Logger LOGGER = LoggerFactory.getLogger(ACrudServiceImpl.class);

    public abstract JpaRepository<E, ID> getRepository();

    private E save(E entity) throws ServiceException {

        // get repo
        JpaRepository<E, ID> repository = getRepository();

        try {
            // save entity
            return repository.save(entity);
        } catch (Exception exception) {
            LOGGER.error(NotifierMicroserviceConstants.ERROR_MESSAGE_ENTITY_SAVE, exception);
            throw new ServiceException(NotifierMicroserviceConstants.ERROR_MESSAGE_ENTITY_SAVE);
        }
    }

    // .
    protected abstract E preInsert(E entity);

    @Override
    public E create(E entity) throws ServiceException {

        // initialize entity to insert
        // e.g setting unique UUID
        E initializedEntity = preInsert(entity);

        return save(initializedEntity);
    }

    @Override
    public List<E> getAll() throws ServiceException {

        // get repo
        JpaRepository<E, ID> repository = getRepository();
        try {
            return repository.findAll();
        } catch (Exception exception) {
            exception.printStackTrace();
            LOGGER.error(NotifierMicroserviceConstants.ERROR_MESSAGE_ENTITY_GET_LIST, exception);
            throw new ServiceException(NotifierMicroserviceConstants.ERROR_MESSAGE_ENTITY_GET_LIST);
        }
    }

    @Override
    public E getById(ID id) throws ServiceException, NotFoundException {

        // get repo
        JpaRepository<E, ID> repository = getRepository();
        Optional<E> entityOptional;

        try {
            // find entity by id
            entityOptional = repository.findById(id);
        } catch (Exception exception) {
            exception.printStackTrace();
            LOGGER.error(NotifierMicroserviceConstants.ERROR_MESSAGE_ENTITY_GET_BY_ID, exception);
            throw new ServiceException(NotifierMicroserviceConstants.ERROR_MESSAGE_ENTITY_GET_BY_ID);
        }

        // get entity or throw exception if empty
        if (entityOptional.isPresent()) {

            return entityOptional.get();
        }

        LOGGER.error(NotifierMicroserviceConstants.ERROR_MESSAGE_ENTITY_NOT_FOUND);
        throw new NotFoundException(NotifierMicroserviceConstants.ERROR_MESSAGE_ENTITY_NOT_FOUND);
    }

    protected abstract E preUpdate(E oldEntity, E newEntity);

    protected abstract ID getId(E entity);

    @Override
    public E update(E newEntity) throws ServiceException {

        // get old entity
        ID id = getId(newEntity);
        E oldEntity = getById(id);

        // prepare entity for update
        E preparedEntity = preUpdate(oldEntity, newEntity);

        return save(preparedEntity);
    }

    @Override
    public boolean delete(E entity) {
        try {

            // get repo
            JpaRepository<E, ID> repository = getRepository();

            // delete entity
            repository.delete(entity);
            return true;
        } catch (Exception exception) {
            LOGGER.error(NotifierMicroserviceConstants.ERROR_MESSAGE_ENTITY_DELETE, exception);
            throw new ServiceException(NotifierMicroserviceConstants.ERROR_MESSAGE_ENTITY_DELETE);
        }
    }

    @Override
    public boolean deleteAll() {

        // get repo
        JpaRepository<E, ID> repository = getRepository();

        try {
            // delete entity
            repository.deleteAll();
            return true;
        } catch (Exception exception) {
            LOGGER.error(NotifierMicroserviceConstants.ERROR_MESSAGE_ENTITY_DELETE, exception);
            throw new ServiceException(NotifierMicroserviceConstants.ERROR_MESSAGE_ENTITY_DELETE);
        }
    }
}
