package com.goudourasv.data.ratings

import com.goudourasv.domain.ratings.Rating

fun RatingEntity.toRating(): Rating {
    return Rating(
        courseId = this.courseEntity.id!!,
        userId = this.userEntity.id,
        userRating = this.userRating,
        message = this.message,
        )
}