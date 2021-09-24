package com.goudourasv.http.instructors

import com.goudourasv.domain.instructors.Instructor
import com.goudourasv.domain.instructors.InstructorsService
import com.goudourasv.http.instructors.dto.InstructorCreate
import com.goudourasv.http.instructors.dto.InstructorUpdate
import io.smallrye.common.annotation.Blocking
import java.net.URI
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@ApplicationScoped
@Path("/instructors")
class InstructorsResource(private val instructorsService: InstructorsService) {

    @Blocking
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getInstructors(@QueryParam("institution") institutionId: UUID?): List<Instructor> {
        return instructorsService.getInstructors(institutionId)

    }

    @Blocking
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getSpecificInstructor(@PathParam("id") instructorId: UUID): Instructor {
        return instructorsService.getSpecificInstructor(instructorId) ?: throw NotFoundException()
    }

    @Blocking
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createInstructor(@Valid instructorCreate: InstructorCreate, uriInfo: UriInfo): Response {
        val createdInstructor = instructorsService.createNewInstructor(instructorCreate)
        val path: String = uriInfo.path
        val location: String = path + createdInstructor.id
        return Response.created(URI.create(location)).entity(createdInstructor).build()
    }

    @Blocking
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun deleteInstructor(@PathParam("id") instructorId: UUID) {
        val deleted = instructorsService.deleteSpecificInstructor(instructorId)
        if (!deleted) {
            throw  NotFoundException("Instructor with id $instructorId doesn't exist")
        }
    }

    @Blocking
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateInstructor(@PathParam("id") instructorId: UUID, @Valid instructorCreate: InstructorCreate): Instructor {
        return instructorsService.replaceInstructor(instructorId, instructorCreate)

    }

    @Blocking
    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun partiallyUpdateInstructor(@PathParam("id") instructorId: UUID, instructorUpdate: InstructorUpdate): Instructor {
        return instructorsService.partiallyUpdateInstructor(instructorId, instructorUpdate)
    }

}