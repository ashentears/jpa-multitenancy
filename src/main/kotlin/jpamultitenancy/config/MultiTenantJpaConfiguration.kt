package jpamultitenancy.config

import jpamultitenancy.domain.Sample
import org.hibernate.cfg.Environment
import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.sql.DataSource


@Configuration
@EnableConfigurationProperties(JpaProperties::class)
class MultiTenantJpaConfiguration {
    @Autowired
    private val dataSource: DataSource? = null

    @Autowired
    private val jpaProperties: JpaProperties? = null
    @Autowired
    private val hibernateProperty: HibernateProperties? = null

    @Autowired
    @Qualifier("dynamicDataSourceBasedMultiTenantConnectionProvider")
    private val multiTenantConnectionProvider: MultiTenantConnectionProvider? = null

    @Autowired
    @Qualifier("currentTenantIdentifierResolver")
    private val currentTenantIdentifierResolver: CurrentTenantIdentifierResolver? = null

    @Bean
    fun entityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        val hibernateProperties: LinkedHashMap<String, Any?> =
            LinkedHashMap<String, Any?>(hibernateProperty!!.determineHibernateProperties(jpaProperties!!.properties, HibernateSettings()))
        hibernateProperties[Environment.MULTI_TENANT_IDENTIFIER_RESOLVER] = currentTenantIdentifierResolver!!
        hibernateProperties[Environment.MULTI_TENANT_CONNECTION_PROVIDER] = multiTenantConnectionProvider!!
        hibernateProperties[Environment.DIALECT] = "org.hibernate.dialect.MySQLDialect"
        hibernateProperties.put("spring.datasource.tomcat.testOnBorrow", true)
        hibernateProperties.put("spring.datasource.tomcat.validationQuery", "select 1")
        return builder.dataSource(dataSource)
            .packages(Sample::class.java.getPackage().name)
            .properties(hibernateProperties)
            .build()
    }
}