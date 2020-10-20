package vip.yazilim.libs.springvip.bean.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.context.annotation.Scope
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import vip.yazilim.libs.springvip.bean.IGenericRestFactory
import vip.yazilim.libs.springvip.bean.IGenericRestLoader
import vip.yazilim.libs.springvip.bean.IGenericRestRegisterer
import vip.yazilim.libs.springvip.ext.libLogDebug
import vip.yazilim.libs.springvip.ext.libLogError
import vip.yazilim.libs.springvip.ext.libLogTrace
import vip.yazilim.libs.springvip.ext.libLogWarn
import vip.yazilim.libs.springvip.util.generic.rest.AGenericRest
import vip.yazilim.libs.springvip.util.generic.rest.GenericRest
import kotlin.reflect.full.findAnnotation

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
        scanner.addIncludeFilter(AnnotationTypeFilter(GenericRest::class.java))
        //TODO scanner.addIncludeFilter(AnnotationTypeFilter(RequestMapping::class.java))

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

        if (!beanClass.isAnnotationPresent(GenericRest::class.java)) {
            libLogWarn("$tag::VipGenericRest annotation not found")
            return
        }
        val genericRest = beanClass.getAnnotation(GenericRest::class.java)

        libLogTrace("$tag::Getting  Bean from Context")
        val restControllerBean = context.getBean(beanClass) as AGenericRest<*, *>

        val uriValues = restControllerBean::class.findAnnotation<RequestMapping>()?.value ?: emptyArray()
        uriValues.forEach { load(restControllerBean, genericRest, it) }
    }

    private fun <E, ID, T : AGenericRest<E, ID>> load(restControllerBean: T, genericRest: GenericRest, uri: String) {
        val tag = "[${restControllerBean::class.qualifiedName}:${uri}]"
        libLogTrace("$tag::Creating Proxy Object for GenericController")
        val proxyRestController = genericRestFactory.buildProxyRestController(restControllerBean, genericRest, uri)

        libLogTrace("$tag::Register Mappings")
        genericRestRegisterer.registerMappings(restControllerBean, genericRest, proxyRestController, uri)

        libLogDebug("$tag::Loaded")
    }
}