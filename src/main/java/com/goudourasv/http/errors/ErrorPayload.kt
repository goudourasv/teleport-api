package com.goudourasv.http.errors

import javax.ws.rs.core.Response

data class ErrorPayload (
    var message: String,
    var status: Response.Status,
)
