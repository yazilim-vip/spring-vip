package vip.yazilim.libs.springvip.bean.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.context.annotation.Scope
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.stereotype.Component
import vip.yazilim.libs.springvip.bean.IGenericRestFactory
import vip.yazilim.libs.springvip.bean.IGenericRestLoader
import vip.yazilim.libs.springvip.bean.IGenericRestRegisterer
import vip.yazilim.libs.springvip.ext.libLogDebug
import vip.yazilim.libs.springvip.ext.libLogError
import vip.yazilim.libs.springvip.ext.libLogTrace
import vip.yazilim.libs.springvip.ext.libLogWarn
import vip.yazilim.libs.springvip.util.generic.rest.VipGenericRest

/**
 *
 * @author maemresen - maemresen@yazilim.vip
 * 17.10.2020
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
open class GenericRestLoader(
        private val context: ConfigurableApplicationContext,
        private val genericRestFactory: IGenericRestFactory,
        private val genericRestRegisterer: IGenericRestRegisterer,
        @Value("\${spring-vip.rest-packages}")
        private val restPackages: Array<String>? = null,
) : IGenericRestLoader {

    init {
        restPackages!!
        libLogTrace("Scanning Packages::${restPackages.joinToString(separator = ",")}")
        restPackages.forEach { loadGenericRestControllers(it) }
    }

    override fun loadGenericRestControllers(packageName: String) {
        val tag = "[$packageName]"
        libLogDebug("$tag::Scanning")
        val scanner = ClassPathScanningCandidateComponentProvider(false)
        scanner.addIncludeFilter(AnnotationTypeFilter(VipGenericRest::class.java))

        /*
        -------------------------------
        | FIND GENERIC REST CONTROLLER
        -------------------------------
        */
        val candidates = scanner.findCandidateComponents(packageName);
        val candidateNames = candidates.joinToString(transform = {
            it.beanClassName.replace("${packageName}.", "")
        }, separator = ",")
        libLogDebug("$tag::Found Candidates::[${candidateNames}]")
        for (beanDefinition in candidates) {
            try {
                load(beanDefinition)
            } catch (e: ClassNotFoundException) {
                libLogError("$tag::Error", e)
            } catch (e: InstantiationException) {
                libLogError("$tag::Error", e)
            } catch (e: IllegalAccessException) {
                libLogError("$tag::Error", e)
            } catch (e: NoSuchMethodException) {
                libLogError("$tag::Error", e)
            }
        }
    }

    private fun load(beanDefinition: BeanDefinition) {
        val tag = "[${beanDefinition.beanClassName}]"

        libLogTrace("$tag::Loading Class")
        val beanClass = Class.forName(beanDefinition.beanClassName);

        if (!beanClass.isAnnotationPresent(VipGenericRest::class.java)) {
            libLogWarn("$tag::VipGenericRest annotation not found")
            return
        }
        val vipGenericRest = beanClass.getAnnotation(VipGenericRest::class.java)

        libLogTrace("$tag::Getting  Bean from Context")
        val restControllerBean = context.getBean(beanClass)

        libLogTrace("$tag::Creating Proxy Object for GenericController")
        val proxyRestController = genericRestFactory.buildProxyRestController(restControllerBean, vipGenericRest)

        libLogTrace("$tag::Register Mappings")
        genericRestRegisterer.registerMappings(restControllerBean::class, proxyRestController, vipGenericRest)

        libLogDebug("$tag::Loaded")
    }
}