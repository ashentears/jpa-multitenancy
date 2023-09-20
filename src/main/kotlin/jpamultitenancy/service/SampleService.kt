package jpamultitenancy.service

import jpamultitenancy.domain.Sample
import jpamultitenancy.domain.Sample1
import jpamultitenancy.domain.Sample2
import jpamultitenancy.repository.Sample1Repository
import jpamultitenancy.repository.Sample2Repository
import jpamultitenancy.repository.SampleRepository
import org.springframework.stereotype.Service

@Service
class SampleService(
    val sampleRepository: SampleRepository,
    val sample1Repository: Sample1Repository,
    val sample2Repository: Sample2Repository,
    ) {
    fun save(sample: Sample1){
        sample1Repository.save(sample)
    }
    fun save(sample: Sample2){
        sample2Repository.save(sample)
    }
    fun save(sample: Sample){
        sampleRepository.save(sample)
    }

    fun findAll(tenant: String): List<Sample>?{
        if(tenant.equals("tenant1")){
            return findAllSample1().stream().map { Sample(id = it.id, name = it.name) }.toList()
        }
        else if(tenant.equals("tenant2")){
            return findAllSample2().stream().map { Sample(id = it.id, name = it.name) }.toList()
        }else return null
    }
    fun findAllSample1(): List<Sample1> = sample1Repository.findAll().toList()
    fun findAllSample2(): List<Sample2> = sample2Repository.findAll().toList()
    fun findAllSample(): List<Sample> = sampleRepository.findAll().toList()
}