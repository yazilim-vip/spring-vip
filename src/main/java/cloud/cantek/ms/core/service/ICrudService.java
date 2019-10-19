package cloud.cantek.ms.core.service;

import java.util.List;

import cloud.cantek.ms.core.exception.NotFoundException;
import cloud.cantek.ms.core.exception.ServiceException;

/**
 * Business method definitions for Category Entity
 *
 * @author Emre Sen, 27.06.2019
 * @contact maemresen07@gmail.com
 */
public interface ICrudService<E, ID> {

    // (C) create Operations
    E create(E entity) throws ServiceException;


    // TODO: list will be converted to set
    // (R) read Operations
    List<E> getAll() throws ServiceException;

    E getById(ID id) throws ServiceException, NotFoundException;


    // (U) update Operations
    E update(E newEntity) throws ServiceException;


    // (D) delete Operations
    boolean delete(E entity);

    boolean deleteAll();
}


