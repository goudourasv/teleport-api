package com.goudourasv.http.tags.dto

import javax.validation.constraints.NotBlank

data class TagCreate(
    @NotBlank
    val name: String,
)