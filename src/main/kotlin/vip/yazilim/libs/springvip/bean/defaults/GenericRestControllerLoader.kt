package vip.yazilim.libs.springvip.bean.defaults

import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.ConfigurableBeanFactory

import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.context.annotation.Scope
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.stereotype.Component
import vip.yazilim.libs.springvip.bean.IGenericRestControllerFactory
import vip.yazilim.libs.springvip.bean.IGenericRestControllerLoader
import vip.yazilim.libs.springvip.bean.IGenericRestControllerRegisterer
import vip.yazilim.libs.springvip.util.generic.rest.VipGenericRest

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 17.10.2020
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
open class GenericRestControllerLoader(
        private val context: ConfigurableApplicationContext,
        private val genericRestFactory: IGenericRestControllerFactory,
        private val genericRestRegisterer: IGenericRestControllerRegisterer,
        @Value("\${spring-vip.rest-package}")
        private val restPackage: String? = null,
) : IGenericRestControllerLoader {

    init {
        this.loadGenericRestControllers(restPackage!!)
    }

    override fun loadGenericRestControllers(packageName: String) {
        val scanner = ClassPathScanningCandidateComponentProvider(false)
        scanner.addIncludeFilter(AnnotationTypeFilter(VipGenericRest::class.java))

        /*
        -------------------------------
        | FIND GENERIC REST CONTROLLER
        -------------------------------
        */
        for (beanDefinition in scanner.findCandidateComponents(packageName)) {
            try {
                val beanClass = Class.forName(beanDefinition.beanClassName);
                if (beanClass.isAnnotationPresent(VipGenericRest::class.java)) {
                    val vipGenericRest = beanClass.getAnnotation(VipGenericRest::class.java);
                    val restControllerBean = context.getBean(beanClass)
                    val proxyRestController = genericRestFactory.buildProxyRestController(restControllerBean, vipGenericRest)
                    genericRestRegisterer.registerMappings(restControllerBean::class, proxyRestController, vipGenericRest)
                }
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            }
        }
    }
}