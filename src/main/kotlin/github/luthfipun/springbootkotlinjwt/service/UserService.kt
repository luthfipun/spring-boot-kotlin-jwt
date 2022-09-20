package github.luthfipun.springbootkotlinjwt.service

import github.luthfipun.springbootkotlinjwt.domain.model.request.RegisterRequest
import github.luthfipun.springbootkotlinjwt.domain.model.response.RegisterLoginResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.UsersResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.WebResponse

interface UserService {
    fun getAllUser(): WebResponse<List<UsersResponse>>
    fun register(registerRequest: RegisterRequest): WebResponse<RegisterLoginResponse>
}