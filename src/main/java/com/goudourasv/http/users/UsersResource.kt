package com.goudourasv.http.users

import com.goudourasv.domain.courses.Course
import com.goudourasv.domain.courses.CoursesService
import com.goudourasv.domain.users.User
import com.goudourasv.domain.users.UsersService
import com.goudourasv.http.users.dto.FavouriteCourseCreate
import com.goudourasv.http.users.dto.UserCreate
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
@Path("/users")
class UsersResource(private val usersService: UsersService, private val coursesService: CoursesService) {

    @Blocking
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun getSpecificUser(@PathParam("id") id: UUID): User {
        return usersService.getSpecificUser(id)
    }

    @Blocking
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createUser(@Valid userCreate: UserCreate, uriInfo: UriInfo): Response {
        val user = usersService.createNewUser(userCreate)
        val path = uriInfo.path
        val location = path + user.id.toString()
        return Response.created(URI.create(location)).entity(user).build()
    }

    @Blocking
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun deleteUser(id: UUID) {
        val deleted = usersService.deleteSpecificUser(id)
        if (!deleted) {
            throw NotFoundException("User with id: $id doesn't exist")
        }
    }

    @Blocking
    @POST
    @Path("/{id}/courses/favourite")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createFavouriteCourse(
        @PathParam("id") userId: UUID,
        favouriteCourseCreate: FavouriteCourseCreate,
        uriInfo: UriInfo
    ): Response {
        favouriteCourseCreate.userId = userId
        val favouriteCourse = coursesService.createFavourite(favouriteCourseCreate)
        val path = uriInfo.path
        val location = path + userId.toString() + favouriteCourseCreate.courseId.toString()
        return Response.created(URI.create(location)).entity(favouriteCourse).build()
    }

    @Blocking
    @DELETE
    @Path("/{id}/courses/favourite")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun deleteFavouriteCourse(@PathParam("id") userId: UUID, @QueryParam("courseId") courseId: UUID) {
        val deleted = coursesService.deleteSpecificFavouriteCourse(userId, courseId)
        if (!deleted) {
            throw NotFoundException("Favourite with userId: $userId and courseId : $courseId doesn't exist")
        }
    }

    @Blocking
    @GET
    @Path("/{id}/courses/favourites")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun favouriteCourses(@PathParam("id") userId: UUID): List<Course> {
        return coursesService.getFavouriteCourses(userId)
    }
}