package jpamultitenancy.service

import jpamultitenancy.domain.Sample
import jpamultitenancy.repository.SampleRepository
import org.springframework.stereotype.Service

@Service
class SampleService(
    val sampleRepository: SampleRepository
    ) {

    fun save(sample: Sample){
        sampleRepository.save(sample)
    }
    fun findAll(): List<Sample> = sampleRepository.findAll().toList()
}