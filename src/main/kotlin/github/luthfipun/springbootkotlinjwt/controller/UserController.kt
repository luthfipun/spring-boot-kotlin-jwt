package github.luthfipun.springbootkotlinjwt.controller

import github.luthfipun.springbootkotlinjwt.domain.model.entity.UserAuth
import github.luthfipun.springbootkotlinjwt.domain.model.request.GrantedRequest
import github.luthfipun.springbootkotlinjwt.domain.model.request.LoginRequest
import github.luthfipun.springbootkotlinjwt.domain.model.request.RegisterRequest
import github.luthfipun.springbootkotlinjwt.domain.model.response.RegisterLoginResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.UsersResponse
import github.luthfipun.springbootkotlinjwt.domain.model.response.WebResponse
import github.luthfipun.springbootkotlinjwt.domain.util.Constant.ROLE_ADMIN
import github.luthfipun.springbootkotlinjwt.domain.util.Constant.ROLE_USER
import github.luthfipun.springbootkotlinjwt.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import javax.annotation.security.RolesAllowed

@RestController
@RequestMapping("api/v1/")
class UserController(
    val userService: UserService
) {
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

    @GetMapping(
        value = ["me"],
        produces = ["application/json"]
    )
    @RolesAllowed(ROLE_USER, ROLE_ADMIN)
    fun profile(authentication: Authentication): WebResponse<UsersResponse> {
        val userAuth = authentication.principal as UserAuth
        return userService.profile(email = userAuth.email)
    }

    @GetMapping(
        value = ["users"],
        produces = ["application/json"]
    )
    @RolesAllowed(ROLE_ADMIN)
    fun getAllUser(): WebResponse<List<UsersResponse>> {
        return userService.getAllUser()
    }

    @PostMapping(
        value = ["grant"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    @RolesAllowed(ROLE_ADMIN)
    fun grantAsAdmin(@RequestBody grantedRequest: GrantedRequest): WebResponse<Nothing> {
        return userService.grantAsAdmin(grantedRequest = grantedRequest)
    }

    @PostMapping(
        value = ["ungrant"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    @RolesAllowed(ROLE_ADMIN)
    fun unGrantAsAdmin(@RequestBody grantedRequest: GrantedRequest): WebResponse<Nothing> {
        return userService.unGrantAsAdmin(grantedRequest = grantedRequest)
    }
}