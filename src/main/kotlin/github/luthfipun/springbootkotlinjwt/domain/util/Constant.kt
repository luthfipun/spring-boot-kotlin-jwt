package github.luthfipun.springbootkotlinjwt.domain.util

object Constant {
    const val ROLE_ADMIN = "ROLE_ADMIN"
    const val ROLE_USER = "ROLE_USER"

    const val JWT_TOKEN_EXPIRED: Long = 20 * 60 * 1000 // 20 min
}