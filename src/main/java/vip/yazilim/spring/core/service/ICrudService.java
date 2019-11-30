package vip.yazilim.spring.core.service;

import java.util.List;
import java.util.Optional;

import vip.yazilim.spring.core.exception.InvalidArgumentException;
import vip.yazilim.spring.core.exception.InvalidUpdateException;
import vip.yazilim.spring.core.exception.database.DatabaseException;
import vip.yazilim.spring.core.exception.web.ServiceException;

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

	// (U) update Operations

	/**
	 * Update table with given model
	 *
	 * @param newEntity new updated values to save into data source
	 * @return saved entity to database
	 * @throws InvalidUpdateException
	 * @throws InvalidArgumentException 
	 * @throws ServiceException       an exception occurred during execution of
	 *                                query
	 */
	E update(E newEntity) throws DatabaseException, InvalidUpdateException, InvalidArgumentException;

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
	 * @throws InvalidArgumentException 
	 * @throws ServiceException an exception occurred during execution of query
	 */
	Optional<E> getById(ID id) throws DatabaseException, InvalidArgumentException;

	// (D) delete Operations
	boolean deleteById(ID id) throws DatabaseException;
	
	boolean delete(E entity) throws DatabaseException;

	boolean deleteAll() throws DatabaseException;
}
