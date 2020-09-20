package vip.yazilim.libs.springvip.util.generic.rest

import vip.yazilim.libs.springvip.util.generic.rest.method.IGenericRestMethodCreate
import vip.yazilim.libs.springvip.util.generic.rest.method.IGenericRestMethodSave
import vip.yazilim.libs.springvip.util.generic.rest.method.IGenericRestMethodUpdate
import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodCreate
import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodSave
import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodUpdate

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen (maemresen@yazilim.vip), 27.06.2019
 */
interface IGenericRestWrite<E, ID> : IGenericRestRead<E, ID>, IGenericRestMethodCreate<E, ID>, IGenericRestMethodUpdate<E, ID>