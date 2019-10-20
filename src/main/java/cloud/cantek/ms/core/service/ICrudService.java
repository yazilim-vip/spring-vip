package cloud.cantek.ms.core.service;

import java.util.List;

import cloud.cantek.ms.core.exception.NotFoundException;
import cloud.cantek.ms.core.exception.ServiceException;

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen, 27.06.2019
 * @contact maemresen07@gmail.com
 */
public interface ICrudService<E, ID> {

	// (C) create Operations
	/**
	 * Save Entity to the Data source.
	 * 
	 * Insert if not exists
	 * 
	 * Update if exists
	 * 
	 * @param entity data to insert
	 * @return inserted entity
	 * @throws ServiceException an exception occurred during execution of query
	 */
	E save(E entity) throws ServiceException;

	/**
	 * Insert Entity to the Data source.
	 * 
	 * @param entity data to insert
	 * @return inserted entity
	 * @throws ServiceException an exception occurred during execution of query
	 */
	E create(E entity) throws ServiceException;

	// TODO: ZZ_OTHER: list will be converted to set
	// (R) read Operations
	/**
	 * Get all entities on the table
	 * 
	 * @return list of entities
	 * @throws ServiceException
	 */
	List<E> getAll() throws ServiceException;

	/**
	 * Get an entity from table by id.
	 * 
	 * @param id id field of entity
	 * @return entity with id
	 * @throws ServiceException  an exception occurred during execution of query
	 * @throws NotFoundException entity with given id not found
	 */
	E getById(ID id) throws ServiceException, NotFoundException;

	// (U) update Operations

	/**
	 * Update table with given model
	 * 
	 * @param newEntity new updated values to save into data source
	 * @return saved entity to database
	 * @throws ServiceException an exception occurred during execution of query
	 */
	E update(E newEntity) throws ServiceException;

	// (D) delete Operations
	boolean delete(E entity);

	boolean deleteAll();
}
