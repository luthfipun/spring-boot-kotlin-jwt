package github.luthfipun.springbootkotlinjwt.service.impl

import github.luthfipun.springbootkotlinjwt.domain.error.ErrorException
import github.luthfipun.springbootkotlinjwt.domain.model.request.RegisterRequest
import github.luthfipun.springbootkotlinjwt.domain.model.response.RegisterLoginResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.UsersResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.WebResponse
import github.luthfipun.springbootkotlinjwt.domain.util.Constant.ROLE_USER
import github.luthfipun.springbootkotlinjwt.domain.validation.ValidationUtil
import github.luthfipun.springbootkotlinjwt.repository.RoleRepository
import github.luthfipun.springbootkotlinjwt.repository.UserRepository
import github.luthfipun.springbootkotlinjwt.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val validationUtil: ValidationUtil,
    val passwordEncoder: PasswordEncoder
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
}