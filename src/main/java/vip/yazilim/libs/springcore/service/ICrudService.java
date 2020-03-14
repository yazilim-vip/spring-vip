package vip.yazilim.libs.springcore.service;

import java.util.List;
import java.util.Optional;

import vip.yazilim.libs.springcore.exception.checked.database.DatabaseException;
import vip.yazilim.libs.springcore.exception.unchecked.RestException;

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen, 27.06.2019
 * @contact maemresen@yazilim.vip
 */
public interface ICrudService<E, ID> {

    /**
     * Save Entity to the Data source.
     * <p>
     * Insert if not exists
     * <p>
     * Update if exists
     *
     * @param entity data to insert
     * @return inserted entity
     * @throws RestException an exception occurred during execution of query
     */
	E save(E entity) throws Exception;

	// (C) create Operations
	/**
	 * Insert Entity to the Data source.
	 *
	 * @param entity data to insert
	 * @return inserted entity
	 * @throws RestException an exception occurred during execution of query
	 */
	E create(E entity) throws DatabaseException;

	// (U) update Operations

	/**
	 * Update table with given model
	 *
	 * @param newEntity new updated values to save into data source
	 * @return saved entity to database
	 * @throws RestException an exception occurred during execution of query
	 */
	E update(E newEntity) throws DatabaseException, IllegalArgumentException;

	// (R) read Operations

	/**
	 * Get all entities on the table
	 *
	 * @return list of entities
	 * @throws RestException
	 */
	List<E> getAll() throws DatabaseException;

	/**
	 * Get an entity from table by id.
	 *
	 * @param id id field of entity
	 * @return entity with id
	 * @throws IllegalArgumentException
	 * @throws RestException            an exception occurred during execution of
	 *                                  query
	 */
	Optional<E> getById(ID id) throws DatabaseException, IllegalArgumentException;

	// (D) delete Operations
	boolean deleteById(ID id) throws DatabaseException;

	boolean delete(E entity) throws DatabaseException;

	boolean deleteAll() throws DatabaseException;

}
