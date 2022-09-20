package github.luthfipun.springbootkotlinjwt.service.impl

import github.luthfipun.springbootkotlinjwt.domain.model.response.UsersResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.WebResponse
import github.luthfipun.springbootkotlinjwt.repository.UserRepository
import github.luthfipun.springbootkotlinjwt.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository
) : UserService {
    override fun getAllUser(): WebResponse<List<UsersResponse>> {
        return WebResponse(data = userRepository.findAll().map { it.toUsersResponse() })
    }
}