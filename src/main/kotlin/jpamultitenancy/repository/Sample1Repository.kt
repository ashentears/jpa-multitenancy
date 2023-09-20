package jpamultitenancy.repository

import jpamultitenancy.domain.Sample1
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface Sample1Repository: CrudRepository<Sample1, Long> {
}