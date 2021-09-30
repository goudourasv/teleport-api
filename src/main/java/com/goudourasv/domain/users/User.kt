package com.goudourasv.domain.users

import java.util.*
import javax.validation.constraints.Email

data class User(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    @Email
    val email: String,
)