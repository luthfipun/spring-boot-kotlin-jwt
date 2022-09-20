package github.luthfipun.springbootkotlinjwt.repository

import github.luthfipun.springbootkotlinjwt.domain.model.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RoleRepository: JpaRepository<Role, Long> {
    fun findByName(name: String): Optional<Role>
}