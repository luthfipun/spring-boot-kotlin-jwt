package github.luthfipun.springbootkotlinjwt.security.jwt

import github.luthfipun.springbootkotlinjwt.domain.model.entity.User
import github.luthfipun.springbootkotlinjwt.domain.model.entity.UserAuth
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.ObjectUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtTokenFilter(
    val jwtTokenUtil: JwtTokenUtil
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        if(!hasAuthorizationBearer(request)){
            filterChain.doFilter(request, response)
            return
        }

        val token: String = getAccessToken(request)

        if (!jwtTokenUtil.validateAccessToken(token = token)){
            filterChain.doFilter(request, response)
            return
        }

        setAuthenticationContext(token, request)
        filterChain.doFilter(request, response)
    }

    private fun setAuthenticationContext(token: String, request: HttpServletRequest) {
        val userDetails: UserDetails = getUserDetails(token)

        val authentication = UsernamePasswordAuthenticationToken(userDetails, null, null)
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication
    }

    private fun getUserDetails(token: String): UserDetails {
        val jwtSubject = jwtTokenUtil.getSubject(token)
        return UserAuth(email = jwtSubject)
    }

    private fun getAccessToken(request: HttpServletRequest): String {
        val header = request.getHeader("Authorization")
        return header.split(" ")[1].trim()
    }

    private fun hasAuthorizationBearer(request: HttpServletRequest): Boolean {
        val header: String? = request.getHeader("Authorization")
        if (header == null || ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")){
            return false
        }
        return true
    }
}