package vip.yazilim.libs.springvip.util.generic.rest

import vip.yazilim.libs.springvip.util.generic.rest.method.IGenericRestMethodDeleteById

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen (maemresen@yazilim.vip), 27.06.2019
 */
interface IGenericRestCrud<E, ID> : IGenericRestWrite<E, ID>, IGenericRestMethodDeleteById<E, ID>
