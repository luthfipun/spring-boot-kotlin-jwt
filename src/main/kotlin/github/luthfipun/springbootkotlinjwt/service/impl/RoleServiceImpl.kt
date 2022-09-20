package github.luthfipun.springbootkotlinjwt.service.impl

import github.luthfipun.springbootkotlinjwt.repository.RoleRepository
import github.luthfipun.springbootkotlinjwt.service.RoleService
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(
    val roleRepository: RoleRepository
) : RoleService {
}