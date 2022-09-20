package github.luthfipun.springbootkotlinjwt.repository

import github.luthfipun.springbootkotlinjwt.domain.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
}