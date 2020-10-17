package vip.yazilim.libs.springvip.util.generic.service

import vip.yazilim.libs.springvip.exception.*
import java.util.*

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen (maemresen@yazilim.vip), 27.06.2019
 */
interface IGenericServiceCrud<E, ID> : IGenericServiceWrite<E, ID>{

    // (D) delete Operations
    /**
     * @return
     * @throws DatabaseDeleteException
     */
    @Throws(DatabaseDeleteException::class)
    fun deleteAll(): Boolean

    /**
     * @param entity
     * @return
     * @throws DatabaseDeleteException
     */
    @Throws(DatabaseDeleteException::class)
    fun delete(entity: E): Boolean

    /**
     * @param id
     * @return
     * @throws DatabaseDeleteException
     */
    @Throws(DatabaseDeleteException::class)
    fun deleteById(id: ID): Boolean
}
