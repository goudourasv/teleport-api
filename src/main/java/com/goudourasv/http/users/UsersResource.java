package com.goudourasv.http.users;

import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.courses.CoursesService;
import com.goudourasv.domain.users.User;
import com.goudourasv.domain.users.UsersService;
import com.goudourasv.http.users.dto.FavouriteCourseCreate;
import com.goudourasv.http.users.dto.UserCreate;
import com.goudourasv.http.users.dto.UserUpdate;
import io.smallrye.common.annotation.Blocking;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Path("/users")
public class UsersResource {
    private final UsersService usersService;
    private final CoursesService coursesService;

    public UsersResource(UsersService usersService, CoursesService coursesService) {
        this.usersService = usersService;
        this.coursesService = coursesService;
    }

    @Blocking
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User getSpecificUser(@PathParam("id") UUID id) {
        User user = usersService.getSpecificUser(id);
        return user;
    }

    @Blocking
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInstructor(@Valid UserCreate userCreate, UriInfo uriInfo) {
        User user = usersService.createNewUser(userCreate);

        String path = uriInfo.getPath();
        String location = path + user.getId().toString();
        return Response.created(URI.create(location)).entity(user).build();
    }

    @Blocking
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteSpecificUser(@PathParam("id") UUID id) {
        boolean deleted = usersService.deleteSpecificUser(id);
        if (!deleted) {
            throw new NotFoundException("User with id: " + id + " doesn't exist");
        }
    }

    @Blocking
    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User partiallyUpdateUser(@PathParam("id") UUID id, UserUpdate userUpdate) {
        User user = usersService.partiallyUpdateUser(id, userUpdate);
        return user;
    }


    @Blocking
    @POST
    @Path("/{id}/courses/favourite")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFavouriteCourse(@PathParam("id") UUID userId, FavouriteCourseCreate favouriteCourseCreate, UriInfo uriInfo) {
        favouriteCourseCreate.setUserId(userId);
        Course favouriteCourse = coursesService.createFavourite(favouriteCourseCreate);

        String path = uriInfo.getPath();
        String location = path + userId.toString() + favouriteCourseCreate.getCourseId();
        return Response.created(URI.create(location)).entity(favouriteCourse).build();

    }

    @Blocking
    @DELETE
    @Path("/{id}/courses/favourite")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteFavouriteCourse(@PathParam("id") UUID userId, @QueryParam("courseId") UUID courseId) {
        boolean deleted = coursesService.deleteSpecificFavouriteCourse(userId, courseId);
        if (!deleted) {
            throw new NotFoundException("Favourite with userId: " + userId + " and courseId :" + courseId + " doesn't exist");
        }
    }

    @Blocking
    @GET
    @Path("/{id}/courses/favourites")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Course> favouriteCourses(@PathParam("id") UUID userId) {
        List<Course> favouriteCourses = coursesService.getFavouriteCourses(userId);
        return favouriteCourses;

    }


}
