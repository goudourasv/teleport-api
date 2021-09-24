package com.goudourasv.domain.institutions

import java.util.*

data class Institution(
    val city: String?,
    val country: String?,
    val id: UUID,
    val name: String,
)