package github.luthfipun.springbootkotlinjwt.domain.seed

import github.luthfipun.springbootkotlinjwt.domain.model.entity.Role
import github.luthfipun.springbootkotlinjwt.domain.model.entity.User
import github.luthfipun.springbootkotlinjwt.domain.util.Constant.ROLE_ADMIN
import github.luthfipun.springbootkotlinjwt.domain.util.Constant.ROLE_USER
import github.luthfipun.springbootkotlinjwt.repository.RoleRepository
import github.luthfipun.springbootkotlinjwt.repository.UserRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserRoleSeeder(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val passwordEncoder: PasswordEncoder
): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {

        // create default roles
        // ROLE_ADMIN | ROLE_USER
        val roleAdmin = Role(name = ROLE_ADMIN)
        if (!roleRepository.findByName(name = ROLE_ADMIN).isPresent){
            roleRepository.save(roleAdmin)
        }

        val roleUser = Role(name = ROLE_USER)
        if (!roleRepository.findByName(name = ROLE_USER).isPresent){
            roleRepository.save(roleUser)
        }

        // create admin dummy
        // email : admin@admin.com
        // pwd : admin123
        val admin = User(
            name = "Admin",
            email = "admin@admin.com",
            password = passwordEncoder.encode("admin123")
        )

        if (!userRepository.findByEmail(email = admin.email).isPresent){
            userRepository.save(admin)
            // set user admin as role admin
            admin.roles.add(roleAdmin)
            userRepository.save(admin)
        }
    }
}