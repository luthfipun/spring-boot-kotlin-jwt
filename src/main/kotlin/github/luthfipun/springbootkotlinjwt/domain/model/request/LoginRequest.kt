package github.luthfipun.springbootkotlinjwt.domain.model.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank
    @field:Email
    @field:Length(min = 3, max = 20)
    val email: String,

    @field:NotBlank
    @field:Length(min = 5, max = 10)
    val password: String
)
