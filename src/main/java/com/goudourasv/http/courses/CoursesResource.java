package com.goudourasv.http.courses;

import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.courses.CoursesService;
import com.goudourasv.domain.courses.LiveCourse;
import com.goudourasv.http.courses.dto.CourseCreate;
import com.goudourasv.http.courses.dto.CourseUpdate;
import io.smallrye.common.annotation.Blocking;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
@Path("/courses")
public class CoursesResource {
    private final CoursesService coursesService;

    // @Inject not needed in Quarkus
    public CoursesResource(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @Blocking
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Course> getCourses(@QueryParam("institution") UUID institutionId, @QueryParam("tags") Set<String> tags, @QueryParam("instructor") UUID instructorId) {
        List<Course> filteredCourses = coursesService.getFilteredCourses(institutionId, tags, instructorId);
        return filteredCourses;
    }

    @Blocking
    @GET
    @Path("/live")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<LiveCourse> getLiveCourses() {
        List<LiveCourse> liveCourses = coursesService.getLiveCourses();

        return liveCourses;
    }

    @Blocking
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getCourse(@PathParam("id") UUID id) {
        Course specificCourse = coursesService.getSpecificCourse(id);
        if (specificCourse == null) {
            throw new NotFoundException();
        }
        return specificCourse;
    }


    @Blocking
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCourse(@Valid CourseCreate courseCreate, UriInfo uriInfo) {
        Course createdCourse = coursesService.createCourse(courseCreate);

        String path = uriInfo.getPath();
        String location = path + createdCourse.getId();
        return Response.created(URI.create(location)).entity(createdCourse).build();
    }

    @Blocking
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteCourse(@PathParam("id") UUID id) {
        boolean deleted = coursesService.deleteSpecificCourse(id);
        if (!deleted) {
            throw new NotFoundException("Course with id: " + id + "doesn't exist");
        }
    }

    @Blocking
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Course updateCourse(@PathParam("id") UUID id, @Valid CourseCreate courseCreate) {
        Course updatedCourse = coursesService.replaceCourse(courseCreate, id);
        return updatedCourse;
    }

    @Blocking
    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Course partiallyUpdateCourse(@PathParam("id") UUID id, CourseUpdate courseUpdate) {
        Course updatedCourse = coursesService.partiallyUpdateCourse(courseUpdate, id);
        return updatedCourse;
    }


}

