package jpamultitenancy.multitenancy.interceptor

import jpamultitenancy.multitenancy.util.DataSourceBasedMultiTenantConnectionProviderImpl
import jpamultitenancy.multitenancy.util.TenantContext
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.sql.Connection
import java.util.Properties
import javax.sql.DataSource

@Component
class TenantInterceptor: WebRequestInterceptor{
    override fun preHandle(request: WebRequest) {
        if (request.getHeader("x-tenant-id") != null) {
            val tenantId = request.getHeader("x-tenant-id")
            println("from header: ${tenantId}")
            TenantContext.setCurrentTenant(tenantId)
        }
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
        println("Nothing to do with postHandle")
    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {
        TenantContext.clear()
    }
}