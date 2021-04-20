package com.goudourasv.courses.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goudourasv.courses.controller.dto.CourseCreate;
import com.goudourasv.courses.controller.dto.CourseUpdate;
import com.goudourasv.courses.domain.Course;
import com.goudourasv.courses.service.CoursesService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/courses")
public class CoursesResource {
    private final CoursesService coursesService = new CoursesService();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses(@QueryParam("institution") String institution, @QueryParam("tag") String tag, @QueryParam("professor") String professor) {
        try {
            List<Course> coursesList = coursesService.getCourses();
            List<Course> filteredList = coursesService.getFilteredList(coursesList, institution, tag, professor);
            return filteredList;
        } catch (Exception ex) {
            throw new ServerErrorException("Something went wrong", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getCourse(@PathParam("id") UUID id) {
        Course course = coursesService.getSpecificCourse(id);
        return course;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCourse(CourseCreate input, UriInfo uriInfo) {

        CoursesValidator.validate(input);
        Course createdCourse = coursesService.createNewCourseInput(input);
        String path = uriInfo.getPath();

        String location = path + "/" + createdCourse.getId();
        return Response.created(URI.create(location)).entity(createdCourse).build();
    }


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


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Course updateCourse(@PathParam("id") UUID id, CourseCreate course) {
        CoursesValidator.validate(course);
        try {
            Course updatedCourse = coursesService.replaceCourse(course, id);
            return updatedCourse;
        } catch (Exception ex) {
            throw new ClientErrorException("Not possible to update the existing resource", Response.Status.CONFLICT);
        }
    }


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

