package com.goudourasv.http.users.dto

import java.util.*

data class FavouriteCourseCreate(
    var courseId: UUID? = null,
    var userId: UUID? = null,
)
