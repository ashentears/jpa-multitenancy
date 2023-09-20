package jpamultitenancy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class JpaMultitenancyApplication

fun main(args: Array<String>) {
	runApplication<JpaMultitenancyApplication>(*args)
}
