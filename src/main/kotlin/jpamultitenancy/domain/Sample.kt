package jpamultitenancy.domain

import jakarta.persistence.*

@Entity
@Table(name = "sample_data")
data class Sample(@Id val id: Int, val name: String){
}


@Entity
@Table(name = "sample_data", schema = "tenant1")
data class Sample1(@Id val id: Int, val name: String) {
}

@Entity
@Table(name = "sample_data", schema = "tenant2")
data class Sample2(@Id val id: Int, val name: String) {
}