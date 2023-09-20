package jpamultitenancy.controller

import jpamultitenancy.domain.Sample
import jpamultitenancy.multitenancy.util.TenantContext
import jpamultitenancy.service.SampleService
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


@RestController
@Transactional
class SampleController(
    val sampleService: SampleService
) {
    @PostMapping("/sample")
    fun addData(
        @RequestParam id: Int, @RequestParam name: String):ResponseEntity<String> {
        val tenant = TenantContext.getCurrentTenant()
        println("current id = ${tenant}")
        val newSample = Sample(id = id, name = name)
        sampleService.save(newSample)
        return ResponseEntity.ok("S0000")
    }

    @GetMapping("/sample")
    fun queryAll(): ResponseEntity<List<Sample>>{
        println("current tenant: {}".format(TenantContext.getCurrentTenant()))
        return ResponseEntity.ok(sampleService.findAll())
    }
}