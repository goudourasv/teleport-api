package com.goudourasv.domain.ratings

import java.util.*

data class Rating(
    val courseId: UUID,
    val userId: UUID,
    val userRating: Int,
    val message: String?,
)
