package com.goudourasv.http.courses;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.courses.CoursesService;
import com.goudourasv.http.courses.dto.CourseCreate;
import com.goudourasv.http.courses.dto.CourseUpdate;
import io.smallrye.common.annotation.Blocking;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.net.URI;
import java.util.List;
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
    public List<Course> getCourses(@QueryParam("institution") String institution, @QueryParam("tag") String tag, @QueryParam("instructor") String instructor) {
        try {
            List<Course> coursesList = coursesService.getCourses();
            List<Course> filteredList = coursesService.getFilteredList(coursesList, institution, tag, instructor);
            return filteredList;
        } catch (Exception ex) {
            throw new ServerErrorException("Something went wrong", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Blocking
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getCourse(@PathParam("id") UUID id) {
        Course course = coursesService.getSpecificCourse(id);
        return course;
    }

    @Blocking
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCourse(@Valid CourseCreate input, UriInfo uriInfo) {
        Course createdCourse = coursesService.createNewCourse(input);

        String path = uriInfo.getPath();
        String location = path + "/" + createdCourse.getId();
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
    public Course updateCourse(@PathParam("id") UUID id, @Valid CourseCreate course) {
        Course updatedCourse = coursesService.replaceCourse(course, id);
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


    private List<Course> parseJsonFile() {
        List<Course> coursesList;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String file = Thread.currentThread().getContextClassLoader().getResource("/courses.json").getFile();
            coursesList = mapper.readValue(new File(file), new TypeReference<>() {
            });
        } catch (Exception ex) {
            throw new ServerErrorException("Something went wrong", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return coursesList;
    }
}

