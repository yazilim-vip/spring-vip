package vip.yazilim.libs.springvip.util.generic.service

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen, 27.06.2019
 * @contact maemresen@yazilim.vip
 */
interface IGenericServiceCrud<E, ID> : IGenericServiceRead<E, ID>, IGenericServiceWrite<E, ID>, IGenericServiceDelete<E, ID>
