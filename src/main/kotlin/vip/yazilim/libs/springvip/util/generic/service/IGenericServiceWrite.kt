package vip.yazilim.libs.springvip.util.generic.service

import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodCreate
import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodSave
import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodUpdate

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen (maemresen@yazilim.vip), 27.06.2019
 */
interface IGenericServiceWrite<E, ID> : IGenericServiceMethodSave<E, ID>, IGenericServiceMethodCreate<E, ID>, IGenericServiceMethodUpdate<E, ID>