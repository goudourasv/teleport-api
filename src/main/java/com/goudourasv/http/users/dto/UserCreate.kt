package com.goudourasv.http.users.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class UserCreate(
    @NotBlank val firstName: String,
    @NotBlank val lastName: String,
    @Email val email: String?,
)
