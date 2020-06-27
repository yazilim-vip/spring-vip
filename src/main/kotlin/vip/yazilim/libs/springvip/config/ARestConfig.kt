package vip.yazilim.libs.springvip.config

import vip.yazilim.libs.springvip.service.ICrudService
import kotlin.reflect.KClass

abstract class ARestConfig<E : Any, ID>(val springVipConfig: SpringVipConfig
                                        , val restResponseGenerator: IRestResponseGenerator<Any, *>
                                        , val crudService: ICrudService<E, ID>
                                        , val classOfEntity: KClass<E>
)
