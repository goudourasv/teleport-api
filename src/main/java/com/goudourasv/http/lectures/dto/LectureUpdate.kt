package com.goudourasv.http.lectures.dto

import java.net.URI
import java.time.Instant
import java.util.*
import javax.validation.constraints.NotBlank

data class LectureUpdate(
    var title: String?,
    var uri: URI?,
    var startTime: Instant?,
    var endTime: Instant?,
    var courseId: UUID?,
)