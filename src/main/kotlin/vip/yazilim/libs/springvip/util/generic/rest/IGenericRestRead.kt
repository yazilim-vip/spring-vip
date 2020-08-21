package vip.yazilim.libs.springvip.util.generic.rest

import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodGetAll
import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodGetById

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen, 27.06.2019
 * @contact maemresen@yazilim.vip
 */
interface IGenericRestRead<E, ID> : IGenericServiceMethodGetAll<E, ID>, IGenericServiceMethodGetById<E, ID>
