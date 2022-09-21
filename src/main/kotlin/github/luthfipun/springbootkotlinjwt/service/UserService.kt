package github.luthfipun.springbootkotlinjwt.service

import github.luthfipun.springbootkotlinjwt.domain.model.request.GrantedRequest
import github.luthfipun.springbootkotlinjwt.domain.model.request.LoginRequest
import github.luthfipun.springbootkotlinjwt.domain.model.request.RegisterRequest
import github.luthfipun.springbootkotlinjwt.domain.model.response.RegisterLoginResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.UsersResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.WebResponse

interface UserService {
    fun register(registerRequest: RegisterRequest): WebResponse<RegisterLoginResponse>
    fun login(loginRequest: LoginRequest): WebResponse<RegisterLoginResponse>
    fun profile(email: String): WebResponse<UsersResponse>
    fun getAllUser(): WebResponse<List<UsersResponse>>
    fun grantAsAdmin(grantedRequest: GrantedRequest): WebResponse<Nothing>
    fun unGrantAsAdmin(grantedRequest: GrantedRequest): WebResponse<Nothing>
}