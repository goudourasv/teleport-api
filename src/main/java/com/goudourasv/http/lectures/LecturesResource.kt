package com.goudourasv.http.lectures

import com.goudourasv.domain.lectures.Lecture
import com.goudourasv.domain.lectures.LecturesService
import com.goudourasv.http.lectures.dto.LectureCreate
import com.goudourasv.http.lectures.dto.LectureUpdate
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
@Path("/lectures")
class LecturesResource(private val lecturesService: LecturesService) {

    @Blocking
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getLectures(@QueryParam("courseId)") courseId: UUID?): List<Lecture> {
        return lecturesService.getFilteredLectures(courseId)
    }

    @Blocking
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getSpecificLecture(@PathParam("id") lectureId: UUID): Lecture {
        return lecturesService.getSpecificLecture(lectureId)
    }

    @Blocking
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createLecture(@Valid lectureCreate: LectureCreate, uriInfo: UriInfo): Response {
        val createdLecture = lecturesService.createLecture(lectureCreate)
        val path = uriInfo.path
        val location = path + "/" + createdLecture.id
        return Response.created(URI.create(location)).entity(createdLecture).build()
    }

    @Blocking
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun deleteLecture(@PathParam("id") lectureId: UUID) {
        val deleted = lecturesService.deleteSpecificLecture(lectureId)
        if (!deleted) {
            throw NotFoundException("Lecture with id: $lectureId  doesn't exist")
        }
    }

    @Blocking
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateLecture(@Valid lectureCreate: LectureCreate, id: UUID): Lecture {
        return lecturesService.replaceLecture(id, lectureCreate)
    }

    @Blocking
    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun partiallyUpdateLecture(lectureUpdate: LectureUpdate, id: UUID): Lecture {
        return lecturesService.partiallyUpdateLecture(id, lectureUpdate)
    }

}