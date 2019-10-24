package cloud.cantek.ms.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cloud.cantek.ms.core.exception.DatabaseException;
import cloud.cantek.ms.core.exception.OctocloudException;

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

	/**
	 * Repository of the entity that will be used with this implementation
	 */
	protected abstract JpaRepository<E, ID> getRepository();

	/**
	 * Get Id of the entity
	 *E
	 * @param entity input entity model
	 * @return id of entity
	 */
	protected abstract ID getId(E entity);

	public E save(E entity) throws DatabaseException {
		try {
			JpaRepository<E, ID> repository = getRepository();
			// save entity
			return repository.save(entity);
		} catch (Exception exception) {
			String errorMessage = String.format("An error occurred while saving Entity[%s]", getId(entity));
			throw new DatabaseException(errorMessage, exception);
		}
	}

	@Override
	public E create(E entity) throws OctocloudException, DatabaseException {

		JpaRepository<E, ID> repository = getRepository();

		ID id = getId(entity);

		boolean eventExists;
		try {
			eventExists = repository.existsById(id);
		} catch (Exception exception) {
			String errorMessage = String.format("An error occurred while searching Entity[%s]", getId(entity));
			throw new DatabaseException(errorMessage, exception);
		}

		if (eventExists) {
			String errorMessage = String.format("Event with id[%s] already exists.", id);
			throw new OctocloudException(errorMessage);
		}

		// initialize entity to insert
		// e.g setting unique UUID
		E initializedEntity = preInsert(entity);

		return save(initializedEntity);
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
	public List<E> getAll() throws DatabaseException {
		try {
			JpaRepository<E, ID> repository = getRepository();
			return repository.findAll();
		} catch (Exception exception) {
			throw new DatabaseException("An error occurred while getting entities", exception);
		}
	}

	@Override
	public E getById(ID id) throws DatabaseException {
		// get repo
		Optional<E> entityOptional;

		try {
			JpaRepository<E, ID> repository = getRepository();
			// find entity by id
			entityOptional = repository.findById(id);
		} catch (Exception exception) {
			String errorMessage = String.format("An error occurred while getting Entity[%s]", id.toString());
			throw new DatabaseException(errorMessage, exception);
		}

		// get entity or throw exception if empty
		return entityOptional.orElse(null);
	}

	@Override
	public E update(E newEntity) throws DatabaseException {
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
	public boolean delete(E entity) throws DatabaseException {
		try {
			JpaRepository<E, ID> repository = getRepository();

			// delete entity
			repository.delete(entity);
			return true;
		} catch (Exception exception) {
			ID id = getId(entity);
			String errorMessage = String.format("An error occurred while deleting Entity[%s]", id.toString());
			throw new DatabaseException(errorMessage, exception);
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
			throw new DatabaseException("An error occurred while deleting entities", exception);
		}
	}
}
