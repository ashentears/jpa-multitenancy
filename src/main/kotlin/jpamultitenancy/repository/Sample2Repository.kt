package jpamultitenancy.repository

import jpamultitenancy.domain.Sample2
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface Sample2Repository: CrudRepository<Sample2, Long> {
}