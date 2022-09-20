package github.luthfipun.springbootkotlinjwt.domain.model.response

import org.springframework.http.HttpStatus

data class WebResponse<T>(
    val code: Int = HttpStatus.OK.value(),
    val message: String = HttpStatus.OK.name,
    val data: T? = null
)
