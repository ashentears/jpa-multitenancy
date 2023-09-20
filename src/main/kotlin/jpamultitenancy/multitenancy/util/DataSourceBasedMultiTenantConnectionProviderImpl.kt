package jpamultitenancy.multitenancy.util

import com.zaxxer.hikari.HikariDataSource
import jakarta.annotation.PostConstruct
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cglib.core.internal.LoadingCache
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import javax.sql.DataSource


@Component("dynamicDataSourceBasedMultiTenantConnectionProvider")
class DataSourceBasedMultiTenantConnectionProviderImpl: AbstractDataSourceBasedMultiTenantConnectionProviderImpl() {
    @Autowired
    @Qualifier("defaultDataSource")
    val defaultDataSource: DataSource? = null
    @Autowired
    @Qualifier("schema1DataSource")
    val schema1DataSource: DataSource? = null
    @Autowired
    @Qualifier("schema2DataSource")
    val schema2DataSource: DataSource? = null

    var map = HashMap<String, DataSource>()

    @PostConstruct
    fun load(){
        map["default"] = defaultDataSource!!
        map["tenant1"] = schema1DataSource!!
        map["tenant2"] = schema2DataSource!!
    }
    public override fun selectAnyDataSource(): DataSource {
        return map["default"]!!
    }

    public override fun selectDataSource(tenantIdentifier: String?): DataSource {
        return map[tenantIdentifier]!!
    }

}
