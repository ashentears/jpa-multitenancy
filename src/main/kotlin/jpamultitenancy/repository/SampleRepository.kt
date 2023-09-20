package jpamultitenancy.repository

import jpamultitenancy.domain.Sample
import org.springframework.data.repository.CrudRepository

interface SampleRepository: CrudRepository<Sample, Long> {
}