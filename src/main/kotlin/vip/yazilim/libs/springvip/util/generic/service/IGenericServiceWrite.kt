package vip.yazilim.libs.springvip.util.generic.service

import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodCreate
import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodSave
import vip.yazilim.libs.springvip.util.generic.service.method.IGenericServiceMethodUpdate

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen, 27.06.2019
 * @contact maemresen@yazilim.vip
 */
interface IGenericServiceWrite<E, ID> : IGenericServiceMethodSave<E, ID>, IGenericServiceMethodCreate<E, ID>, IGenericServiceMethodUpdate<E, ID>