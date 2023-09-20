package jpamultitenancy.domain

import jakarta.persistence.*

@Entity
@Table(name = "sample_data")
data class Sample(@Id val id: Int, val name: String){
}