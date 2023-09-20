package jpamultitenancy.multitenancy.datasource

import jpamultitenancy.multitenancy.util.TenantContext
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

class MultitenantDataSource(): AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): String? {
        return TenantContext.getCurrentTenant()
    }
}