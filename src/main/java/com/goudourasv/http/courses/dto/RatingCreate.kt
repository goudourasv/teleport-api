package com.goudourasv.http.courses.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class RatingCreate(
    val userRating: Int,
    val message: String,
    @JsonProperty("user")
    val userId: UUID,

    )
