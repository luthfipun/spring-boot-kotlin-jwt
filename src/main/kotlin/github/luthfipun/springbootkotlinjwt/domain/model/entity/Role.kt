package github.luthfipun.springbootkotlinjwt.domain.model.entity

import javax.persistence.*
import javax.persistence.GenerationType.AUTO

@Entity
@Table(name = "role")
data class Role(
    @Id
    @GeneratedValue(strategy = AUTO)
    val id: Long = 0,

    @Column(name = "name", unique = true)
    val name: String,

    @ManyToMany(mappedBy = "roles")
    val user: List<User> = mutableListOf()
)
