package vip.yazilim.libs.springvip.util.generic.service

import vip.yazilim.libs.springvip.util.generic.service.method.*

/**
 * Business method definitions for CRUD operations for Entity
 *
 * @author Emre Sen (maemresen@yazilim.vip),  27.06.2019
 */
interface IGenericServiceDelete<E, ID> : IGenericServiceMethodDelete<E, ID>, IGenericServiceMethodDeleteById<E, ID>, IGenericServiceMethodDeleteAll<E, ID>