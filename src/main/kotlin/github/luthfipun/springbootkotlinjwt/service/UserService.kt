package github.luthfipun.springbootkotlinjwt.service

import github.luthfipun.springbootkotlinjwt.domain.model.response.UsersResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.WebResponse

interface UserService {
    fun getAllUser(): WebResponse<List<UsersResponse>>
}