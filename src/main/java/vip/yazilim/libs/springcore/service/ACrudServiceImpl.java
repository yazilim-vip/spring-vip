package vip.yazilim.libs.springcore.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vip.yazilim.libs.springcore.exception.DatabaseCreateException;
import vip.yazilim.libs.springcore.exception.DatabaseDeleteException;
import vip.yazilim.libs.springcore.exception.DatabaseReadException;
import vip.yazilim.libs.springcore.exception.DatabaseSaveException;
import vip.yazilim.libs.springcore.exception.DatabaseUpdateException;
import vip.yazilim.libs.springcore.util.ListHelper;

/**
 * Abstract Implementation of ICrudService.
 * <p>
 * Basic CRUD operations for an entity is implemented.
 *
 * @param <E>  type entity
 * @param <ID> type of identity of entity
 * @author Emre Sen, 19.07.2019
 * @contact maemresen@yazilim.vip
 */
public abstract class ACrudServiceImpl<E, ID> implements ICrudService<E, ID> {

	/**
	 * Repository of the entity that will be used with this implementation
	 */
	protected abstract JpaRepository<E, ID> getRepository();

	protected abstract Class<E> getClassOfEntity();

	/**
	 * Get Id of the entity
	 *
	 * @param entity input entity model
	 * @return id of entity
	 */
	protected abstract ID getId(E entity);

	public E save(E entity) throws DatabaseSaveException {
		try {
			JpaRepository<E, ID> repository = this.getRepository();
			if (entity == null) {
				throw new IllegalArgumentException("Entity to save cannot be null");
			}
			E savedEntity = repository.save(entity);
			if (savedEntity == null) {
				throw new NoSuchElementException("Saved entity not found");
			}
			return savedEntity;
		} catch (Exception exception) {
			throw new DatabaseSaveException(entity.getClass(), getId(entity), exception);
		}
	}

	@Override
	public E create(E entity) throws DatabaseCreateException {
		try {
			// initialize entity to insert
			// e.g setting unique UUID
			E initializedEntity = preInsert(entity);
			return save(initializedEntity);
		} catch (Exception exception) {
			throw new DatabaseCreateException(getClassOfEntity(), exception);
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
	public E update(E newEntity) throws DatabaseUpdateException {

		try {
			// get old entity
			ID id = getId(newEntity);
			Optional<E> oldEntity = getById(id);
			if (!oldEntity.isPresent()) {
				throw new IllegalArgumentException("Cannot update non-exist enity");
			}

			// prepare entity for update
			E preparedEntity = preUpdate(oldEntity.get(), newEntity);
			return save(preparedEntity);
		} catch (Exception exception) {
			throw new DatabaseUpdateException(getClassOfEntity(), getId(newEntity), exception);
		}
	}

	@Override
	public List<E> getAll() throws DatabaseReadException {
		try {
			JpaRepository<E, ID> repository = getRepository();
			List<E> resultList = repository.findAll();
			return ListHelper.getSafeList(resultList);
		} catch (Exception exception) {
			throw new DatabaseReadException(getClassOfEntity(), exception);
		}
	}

	@Override
	public Optional<E> getById(ID id) throws DatabaseReadException {
		try {
			JpaRepository<E, ID> repository = getRepository();
			if (id == null) {
				throw new IllegalArgumentException("ID cannot be null");
			}

			// find entity by id
			return repository.findById(id);
		} catch (Exception exception) {
			throw new DatabaseReadException(getClassOfEntity(), exception);
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
	public boolean deleteById(ID id) throws DatabaseDeleteException {
		try {
			JpaRepository<E, ID> repository = getRepository();

			// delete entity
			repository.deleteById(id);
			return true;
		} catch (Exception exception) {
			throw new DatabaseDeleteException(getClassOfEntity(), exception);
		}
	}

	@Override
	public boolean delete(E entity) throws DatabaseDeleteException {
		try {
			JpaRepository<E, ID> repository = getRepository();

			// delete entity
			repository.delete(entity);
			return true;
		} catch (Exception exception) {
			throw new DatabaseDeleteException(getClassOfEntity(), exception);
		}
	}

	@Override
	public boolean deleteAll() throws DatabaseDeleteException {
		try {
			JpaRepository<E, ID> repository = getRepository();

			// delete entity
			repository.deleteAll();
			return true;
		} catch (Exception exception) {
			throw new DatabaseDeleteException(getClassOfEntity(), exception);
		}
	}
}
