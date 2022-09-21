package github.luthfipun.springbootkotlinjwt.controller

import github.luthfipun.springbootkotlinjwt.domain.model.request.LoginRequest
import github.luthfipun.springbootkotlinjwt.domain.model.request.RegisterRequest
import github.luthfipun.springbootkotlinjwt.domain.model.response.RegisterLoginResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.UsersResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.WebResponse
import github.luthfipun.springbootkotlinjwt.domain.util.Constant
import github.luthfipun.springbootkotlinjwt.domain.util.Constant.ROLE_ADMIN
import github.luthfipun.springbootkotlinjwt.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed

@RestController
@RequestMapping("api/v1/")
class UserController(
    val userService: UserService
) {
    @GetMapping(
        value = ["admin"],
        produces = ["application/json"]
    )
    @RolesAllowed(ROLE_ADMIN)
    fun getAllUser(): WebResponse<List<UsersResponse>> {
        return userService.getAllUser()
    }

    @PostMapping(
        value = ["register"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun register(@RequestBody registerRequest: RegisterRequest): WebResponse<RegisterLoginResponse>{
        return userService.register(registerRequest = registerRequest)
    }

    @PostMapping(
        value = ["login"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun login(@RequestBody loginRequest: LoginRequest): WebResponse<RegisterLoginResponse>{
        return userService.login(loginRequest = loginRequest)
    }
}