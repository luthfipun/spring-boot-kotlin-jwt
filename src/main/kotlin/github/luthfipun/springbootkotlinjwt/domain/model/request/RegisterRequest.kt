package github.luthfipun.springbootkotlinjwt.domain.model.request

import github.luthfipun.springbootkotlinjwt.domain.model.entity.User
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class RegisterRequest(
    @field:NotBlank
    @field:Length(min = 3, max = 50)
    val name: String,

    @field:NotBlank
    @field:Email
    @field:Length(min = 3, max = 20)
    val email: String,

    @field:NotBlank
    @field:Length(min = 5, max = 10)
    val password: String
){
    fun toUser(): User {
        return User(
            name = this.name,
            email = this.email,
            password = this.password
        )
    }
}
