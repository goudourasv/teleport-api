package com.goudourasv.domain.lectures

import java.net.URI
import java.time.Instant
import java.util.*

data class LectureData(
    val id: UUID,
    val title: String,
    val uri: URI?,
    val startTime: Instant,
    val endTime: Instant,
)
