package github.luthfipun.springbootkotlinjwt.security.jwt

import github.luthfipun.springbootkotlinjwt.domain.model.entity.UserAuth
import github.luthfipun.springbootkotlinjwt.domain.util.AppProperties
import github.luthfipun.springbootkotlinjwt.domain.util.Constant.JWT_TOKEN_EXPIRED
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenUtil(
    appProperties: AppProperties
) {
    private val base64EncodeKey: ByteArray = Base64.getEncoder().encode(appProperties.plainSecretKey.toByteArray())
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(base64EncodeKey)

    fun generateAccessToken(userAuth: UserAuth): String {
        return Jwts.builder()
            .setSubject(userAuth.email)
            .setIssuer("@luthfipun")
            .claim("roles", userAuth.roles.joinToString())
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRED))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateAccessToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
            true
        }catch (e: JwtException){
            false
        }
    }

    fun parseClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
    }
}