package jpamultitenancy.multitenancy.util

import org.apache.commons.lang3.StringUtils
import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.springframework.stereotype.Component

@Component("currentTenantIdentifierResolver")
class DefaultCurrentTenantIdentifierResolver: CurrentTenantIdentifierResolver {
    private val defaultTenant = "default"
    override fun resolveCurrentTenantIdentifier(): String {
        val tenantId: String? = TenantContext.getCurrentTenant()
        return tenantId
            ?: if (defaultTenant != null) {
                this.defaultTenant
            } else {
                throw IllegalStateException("No tenant selected")
            }
    }

    override fun validateExistingCurrentSessions(): Boolean {
        return true
    }
}