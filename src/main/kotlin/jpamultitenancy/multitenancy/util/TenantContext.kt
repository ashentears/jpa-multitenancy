package jpamultitenancy.multitenancy.util

object TenantContext{
    private var currentTenant = InheritableThreadLocal<String>()

    fun getCurrentTenant(): String? {
        return currentTenant.get()
    }

    fun setCurrentTenant(tenant: String?) {
        println("set tenantid to : ${tenant}")
        currentTenant.set(tenant)
    }

    fun clear() {
        currentTenant.remove()
    }
}