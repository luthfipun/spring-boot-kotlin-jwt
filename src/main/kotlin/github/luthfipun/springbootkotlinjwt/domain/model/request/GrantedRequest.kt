package github.luthfipun.springbootkotlinjwt.domain.model.request

import javax.validation.constraints.NotNull

data class GrantedRequest(
    @field:NotNull
    val userId: Long
)
