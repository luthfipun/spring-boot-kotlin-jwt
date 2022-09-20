package github.luthfipun.springbootkotlinjwt.service.impl

import github.luthfipun.springbootkotlinjwt.domain.error.ErrorException
import github.luthfipun.springbootkotlinjwt.domain.model.entity.UserAuth
import github.luthfipun.springbootkotlinjwt.domain.model.request.LoginRequest
import github.luthfipun.springbootkotlinjwt.domain.model.request.RegisterRequest
import github.luthfipun.springbootkotlinjwt.domain.model.response.RegisterLoginResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.UsersResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.WebResponse
import github.luthfipun.springbootkotlinjwt.domain.util.Constant.ROLE_USER
import github.luthfipun.springbootkotlinjwt.domain.validation.ValidationUtil
import github.luthfipun.springbootkotlinjwt.repository.RoleRepository
import github.luthfipun.springbootkotlinjwt.repository.UserRepository
import github.luthfipun.springbootkotlinjwt.security.jwt.JwtTokenUtil
import github.luthfipun.springbootkotlinjwt.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val validationUtil: ValidationUtil,
    val passwordEncoder: PasswordEncoder,
    val authenticationManager: AuthenticationManager,
    val jwtTokenUtil: JwtTokenUtil
) : UserService {

    override fun getAllUser(): WebResponse<List<UsersResponse>> {
        return WebResponse(data = userRepository.findAll().map { it.toUsersResponse() })
    }

    override fun register(registerRequest: RegisterRequest): WebResponse<RegisterLoginResponse> {
        validationUtil.validate(registerRequest)

        if (userRepository.findByEmail(email = registerRequest.email).isPresent){
            throw ErrorException("Email address is already registered!")
        }

        val encodePwdUser = registerRequest.copy(
            password = passwordEncoder.encode(registerRequest.password)
        )
        val user = encodePwdUser.toUser()
        user.roles.add(roleRepository.findByName(name = ROLE_USER).get())
        userRepository.save(user)

        return WebResponse(data = RegisterLoginResponse(access_token = "not yet!"))
    }

    override fun login(loginRequest: LoginRequest): WebResponse<RegisterLoginResponse> {
        validationUtil.validate(loginRequest)

        if (!userRepository.findByEmail(email = loginRequest.email).isPresent){
            throw ErrorException("Email address not registered!")
        }

        try {

            val authentication: Authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password)
            )

            val userAuth: UserAuth = authentication.principal as UserAuth
            val accessToken = jwtTokenUtil.generateAccessToken(userAuth = userAuth)

            return WebResponse(data = RegisterLoginResponse(access_token = accessToken))

        }catch (e: BadCredentialsException){
            throw BadCredentialsException("Unauthorized!")
        }
    }
}