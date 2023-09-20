package jpamultitenancy.controller

import jpamultitenancy.domain.Sample
import jpamultitenancy.domain.Sample1
import jpamultitenancy.domain.Sample2
import jpamultitenancy.multitenancy.datasource.MultitenantDataSource
import jpamultitenancy.multitenancy.util.TenantContext
import jpamultitenancy.service.SampleService
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import javax.sql.DataSource


@RestController
@Transactional
class SampleController(
    val sampleService: SampleService,
    private var ds: DataSource
) {
    private val PATH = "/error"
    fun test(ds: DataSource){
        this.ds = ds
    }
    @RequestMapping("/connectToDb")
    fun doSomething() {
        println(ds)
    }

    fun getErrorPath(): String {
        return PATH
    }
    @PostMapping("/sample")
    fun addData(
        @RequestParam id: Int, @RequestParam name: String):ResponseEntity<String> {
        val tenant = TenantContext.getCurrentTenant()
        println("current id = ${tenant}")


//        if(TenantContext.getCurrentTenant().equals("tenant1")){
//            val newSample = Sample1(id = id, name = name)
//            sampleService.save(newSample)
//            return ResponseEntity.ok("S0000")
//        }
//        else if(TenantContext.getCurrentTenant().equals("tenant2")){
//            val newSample = Sample2(id = id, name = name)
//            sampleService.save(newSample)
//            return ResponseEntity.ok("S0000")
//        }
//        else return ResponseEntity.ok("Failed")
        val newSample = Sample(id = id, name = name)
        sampleService.save(newSample)
        return ResponseEntity.ok("S0000")
    }

    @GetMapping("/sample")
    fun queryAll(): ResponseEntity<List<Sample>>{
        println("current tenant: {}".format(TenantContext.getCurrentTenant()))
//        return ResponseEntity.ok(sampleService.findAll(TenantContext.getCurrentTenant()!!))
        return ResponseEntity.ok(sampleService.findAllSample())
    }
}