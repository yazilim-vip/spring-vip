package vip.yazilim.libs.springvip.service;

import java.util.List;
import java.util.Optional;

import vip.yazilim.libs.springvip.exception.DatabaseCreateException;
import vip.yazilim.libs.springvip.exception.DatabaseDeleteException;
import vip.yazilim.libs.springvip.exception.DatabaseReadException;
import vip.yazilim.libs.springvip.exception.DatabaseSaveException;
import vip.yazilim.libs.springvip.exception.DatabaseUpdateException;

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
     */
    E save(E entity) throws IllegalArgumentException, DatabaseSaveException;

    // (C) create Operations

    /**
     * Insert Entity to the Data source.
     *
     * @param entity data to insert
     * @return inserted entity
     */
    E create(E entity) throws DatabaseCreateException;

    // (U) update Operations

    /**
     * Update table with given model
     *
     * @param newEntity new updated values to save into data source
     * @return saved entity to database
     */
    E update(E newEntity) throws DatabaseUpdateException;

    // (R) read Operations

    /**
     * Get all entities on the table
     *
     * @return list of entities
     */
    List<E> getAll() throws DatabaseReadException;

    /**
     * Get an entity from table by id.
     *
     * @param id id field of entity
     * @return entity with id
     * @throws DatabaseReadException    indicates an error in JPARepository
     * @throws IllegalArgumentException id given as argument is null
     */
    Optional<E> getById(ID id) throws DatabaseReadException;

    // (D) delete Operations

    /**
     * @param id
     * @return
     * @throws DatabaseDeleteException
     */
    boolean deleteById(ID id) throws DatabaseDeleteException;

    /**
     * @param entity
     * @return
     * @throws DatabaseDeleteException
     */
    boolean delete(E entity) throws DatabaseDeleteException;

    /**
     * @return
     * @throws DatabaseDeleteException
     */
    boolean deleteAll() throws DatabaseDeleteException;

}
