package com.goudourasv.http.lectures.dto

import java.net.URI
import java.time.Instant
import java.util.*
import javax.validation.constraints.NotBlank

data class LectureCreate(
    @NotBlank val title: String,
    val uri: URI?,
    val startTime: Instant,
    val endTime: Instant,
    val courseId: UUID,
) {
}