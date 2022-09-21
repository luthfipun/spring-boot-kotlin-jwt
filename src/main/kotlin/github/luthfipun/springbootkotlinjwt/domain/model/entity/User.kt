package github.luthfipun.springbootkotlinjwt.domain.model.entity

import github.luthfipun.springbootkotlinjwt.domain.model.response.UsersResponse
import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "email", unique = true, nullable = false)
    val email: String,

    @Column(name = "password")
    val password: String,

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    val roles: MutableList<Role> = mutableListOf()
) {
    fun toUsersResponse(): UsersResponse {
        return UsersResponse(
            id = this.id,
            name = this.name,
            email = this.email,
            roles = roles.map { it.name }
        )
    }

    fun toUserAuth(): UserAuth {
        return UserAuth(
            email = this.email,
            password = this.password,
            roles = this.roles.map { it.name }
        )
    }
}