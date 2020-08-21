package vip.yazilim.libs.springvip.util.generic.rest

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen, 27.06.2019
 * @contact maemresen@yazilim.vip
 */
interface IGenericRestCrud<E, ID> : IGenericRestRead<E, ID>, IGenericRestWrite<E, ID>, IGenericRestDelete<E, ID>
