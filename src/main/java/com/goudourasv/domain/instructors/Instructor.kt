package com.goudourasv.domain.instructors

import com.goudourasv.domain.institutions.Institution
import java.util.*

data class Instructor(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val institutions: List<Institution>
)



