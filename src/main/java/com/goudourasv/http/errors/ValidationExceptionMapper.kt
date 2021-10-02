package com.goudourasv.http.errors

import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ValidationExceptionMapper : ExceptionMapper<ConstraintViolationException> {
    override fun toResponse(exception: ConstraintViolationException): Response {
        val setOfViolations = exception.constraintViolations
        val listOfViolations: MutableList<ConstraintViolation<*>> = ArrayList()
        listOfViolations.addAll(setOfViolations)
        var message = ""
        var counter = 1
        for (constraintViolation in listOfViolations) {
            val violationMessage = constraintViolation.propertyPath.toString() + " " + constraintViolation.message
            message += if (counter == listOfViolations.size) {
                violationMessage
            } else {
                "$violationMessage, "
            }
            counter++
        }
        val errorPayload = ErrorPayload(message, Response.Status.BAD_REQUEST)
        return Response.status(Response.Status.BAD_REQUEST).entity(errorPayload).build()
    }
}