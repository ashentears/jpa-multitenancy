package jpamultitenancy.config

import jpamultitenancy.multitenancy.interceptor.TenantInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration: WebMvcConfigurer {
    private val tenantInterceptor = TenantInterceptor()

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addWebRequestInterceptor(tenantInterceptor)
    }

}