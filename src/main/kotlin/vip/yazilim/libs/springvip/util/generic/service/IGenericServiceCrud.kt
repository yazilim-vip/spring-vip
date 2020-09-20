package vip.yazilim.libs.springvip.util.generic.service

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen (maemresen@yazilim.vip), 27.06.2019
 */
interface IGenericServiceCrud<E, ID> : IGenericServiceRead<E, ID>, IGenericServiceWrite<E, ID>, IGenericServiceDelete<E, ID>
