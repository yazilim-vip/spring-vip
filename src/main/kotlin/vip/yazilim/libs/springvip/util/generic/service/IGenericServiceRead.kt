package vip.yazilim.libs.springvip.util.generic.service

import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodGetAll
import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodGetById

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen (maemresen@yazilim.vip), 27.06.2019
 */
interface IGenericServiceRead<E, ID> : IGenericServiceMethodGetAll<E, ID>, IGenericServiceMethodGetById<E, ID>
