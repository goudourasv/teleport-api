package com.goudourasv.http.courses

import com.goudourasv.domain.courses.Course
import com.goudourasv.domain.courses.CoursesService
import com.goudourasv.domain.courses.LiveCourse
import com.goudourasv.domain.ratings.Rating
import com.goudourasv.domain.ratings.RatingsService
import com.goudourasv.http.courses.dto.CourseCreate
import com.goudourasv.http.courses.dto.CourseUpdate
import com.goudourasv.http.courses.dto.RatingCreate
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
@Path("/courses")
class CoursesResource(private val coursesService: CoursesService, private val ratingsService: RatingsService) {

    @Blocking
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getCourses(
        @QueryParam("institution") institutionId: UUID?,
        @QueryParam("tags") tags: Set<String>?,
        @QueryParam("instructor") instructorId: UUID?
    ): List<Course> {
        return coursesService.getFilteredCourses(institutionId, tags, instructorId)
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/live")
    @GET
    @Blocking
    fun liveCourses(): List<LiveCourse> {
        val liveCourses: List<LiveCourse> = coursesService.getLiveCourses()
        return liveCourses
    }

    @Blocking
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCourse(@PathParam("id") id: UUID): Course {
        return coursesService.getSpecificCourse(id) ?: throw NotFoundException()
    }

    @Blocking
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createCourse(courseCreate: @Valid CourseCreate, uriInfo: UriInfo): Response {
        val createdCourse = coursesService.createCourse(courseCreate)
        val path = uriInfo.path
        val location = path + createdCourse.id
        return Response.created(URI.create(location)).entity(createdCourse).build()
    }

    @Blocking
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteCourse(@PathParam("id") id: UUID) {
        val deleted = coursesService.deleteSpecificCourse(id)
        if (!deleted) {
            throw NotFoundException("Course with id: $id doesn't exist")
        }
    }

    @Blocking
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateCourse(@PathParam("id") id: UUID, courseCreate: @Valid CourseCreate): Course {
        return coursesService.replaceCourse(id, courseCreate)
    }

    @Blocking
    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun partiallyUpdateCourse(@PathParam("id") id: UUID, courseUpdate: CourseUpdate): Course {
        return coursesService.partiallyUpdateCourse(id, courseUpdate)
    }

    @Blocking
    @POST
    @Path("/{id}/ratings")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createRating(@PathParam("id") courseId: UUID, ratingCreate: RatingCreate): Rating {
        // TODO: Read userId from Authorization header
        val userId = UUID.fromString("38c5f6a0-8319-4a43-bd8d-05c762513179")
        return ratingsService.createRating(courseId, userId, ratingCreate)
    }
}