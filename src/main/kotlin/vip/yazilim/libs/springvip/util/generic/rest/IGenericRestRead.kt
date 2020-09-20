package vip.yazilim.libs.springvip.util.generic.rest

import vip.yazilim.libs.springvip.util.generic.rest.method.IGenericRestMethodGetAll
import vip.yazilim.libs.springvip.util.generic.rest.method.IGenericRestMethodGetById
import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodGetAll
import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodGetById

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen (maemresen@yazilim.vip), 27.06.2019
 */
interface IGenericRestRead<E, ID> : IGenericRestMethodGetAll<E, ID>, IGenericRestMethodGetById<E, ID>
