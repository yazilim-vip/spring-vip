package cloud.cantek.ms.core.service;

import java.util.List;
import java.util.Optional;

import cloud.cantek.ms.core.exception.DatabaseException;
import cloud.cantek.ms.core.exception.InvalidUpdateException;
import cloud.cantek.ms.core.exception.runtime.ServiceException;

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen, 27.06.2019
 * @contact maemresen07@gmail.com
 */
public interface ICrudService<E, ID> {

	// (C) create Operations
	/**
	 * Insert Entity to the Data source.
	 *
	 * @param entity data to insert
	 * @return inserted entity
	 * @throws UniqueConstraintViolationException
	 * @throws PrimaryKeyDuplicationException
	 * @throws ServiceException                   an exception occurred during
	 *                                            execution of query
	 */
	E create(E entity) throws DatabaseException;

	// (R) read Operations

	/**
	 * Get all entities on the table
	 *
	 * @return list of entities
	 * @throws ServiceException
	 */
	List<E> getAll() throws DatabaseException;

	/**
	 * Get an entity from table by id.
	 *
	 * @param id id field of entity
	 * @return entity with id
	 * @throws ServiceException an exception occurred during execution of query
	 */
	Optional<E> getById(ID id) throws DatabaseException;

	// (U) update Operations

	/**
	 * Update table with given model
	 *
	 * @param newEntity new updated values to save into data source
	 * @return saved entity to database
	 * @throws InvalidUpdateException 
	 * @throws ServiceException an exception occurred during execution of query
	 */
	E update(E newEntity) throws DatabaseException, InvalidUpdateException;

	// (D) delete Operations
	boolean delete(E entity) throws DatabaseException;

	boolean deleteAll() throws DatabaseException;
}
