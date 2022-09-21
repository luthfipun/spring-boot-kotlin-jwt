package github.luthfipun.springbootkotlinjwt.domain.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppProperties {
    @Value("\${app.jwt.secret}")
    lateinit var plainSecretKey: String
}