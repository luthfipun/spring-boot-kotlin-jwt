package github.luthfipun.springbootkotlinjwt.domain.model.response

data class UsersResponse(
    val id: Long,
    val name: String,
    val email: String,
    val roles: List<String>?
)
