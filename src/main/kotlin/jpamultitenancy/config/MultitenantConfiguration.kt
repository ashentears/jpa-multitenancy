package jpamultitenancy.config

import jpamultitenancy.multitenancy.datasource.MultitenantDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.io.FileInputStream
import java.io.IOException
import java.nio.file.Paths
import java.util.*
import javax.sql.DataSource


@Configuration
class MultitenantConfiguration {
    @Bean("defaultDataSource")
    @Primary
    @ConfigurationProperties(prefix = "tenants.datasources.postgres")
    fun postgres(): DataSource {
        return DataSourceBuilder.create().build()
    }
    @Bean("schema1DataSource")
    @ConfigurationProperties(prefix = "tenants.datasources.tenant1")
    fun schema1(): DataSource {
        return DataSourceBuilder.create().build()
    }
    @Bean("schema2DataSource")
    @ConfigurationProperties(prefix = "tenants.datasources.tenant2")
    fun schema2(): DataSource {
        return DataSourceBuilder.create().build()
    }

}