package github.luthfipun.springbootkotlinjwt.controller

import github.luthfipun.springbootkotlinjwt.domain.model.response.UsersResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.WebResponse
import github.luthfipun.springbootkotlinjwt.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/")
class UserController(
    val userService: UserService
) {
    @GetMapping(
        value = ["admin"],
        produces = ["application/json"]
    )
    fun getAllUser(): WebResponse<List<UsersResponse>> {
        return userService.getAllUser()
    }
}