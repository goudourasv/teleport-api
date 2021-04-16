package com.goudourasv.courses.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goudourasv.courses.controller.dto.CourseUpdate;
import com.goudourasv.courses.domain.Course;
import com.goudourasv.courses.service.CoursesService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.List;

@Path("/courses")

public class CoursesResource {
    private final CoursesService coursesService = new CoursesService();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //TODO Return courses matching an institution
    //TODO @QueryParam ("tag")
    public List<Course> getCourses(@QueryParam("institution") String institution) {
        try {
            List<Course> coursesList = coursesService.getCourses();
            //TODO query param validation
            if (institution != null) {
                List<Course> filteredList = coursesService.getFilteredList(coursesList, institution);
                return filteredList;
            } else {
                return coursesList;
            }
        } catch (Exception ex) {
            throw new ServerErrorException("Something went wrong", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getCourse(@PathParam("id") int id) {
        Course course = coursesService.getSpecificCourse(id);
        return course;
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course createCourse(Course input) {
        CoursesValidator.validate(input);
        Course createdCourse = coursesService.createNewCourse(input);
        return createdCourse;
    }


    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteCourse(@PathParam("id") int id) {
        boolean deleted = coursesService.deleteSpecificCourse(id);
        if (!deleted) {
            throw new NotFoundException("Course with id: " + id + "doesn't exist");
        }

    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Course updateCourse(@PathParam("id") int id, Course course) {
        try {
            Course updatedCourse = coursesService.replaceCourse(course);
            return updatedCourse;
        } catch (Exception ex) {
            throw new ClientErrorException("Not possible to update the existing resource", Response.Status.CONFLICT);
        }
    }

    //TODO PATCH request
    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Course partiallyUpdateCourse(@PathParam("id") int id, CourseUpdate courseUpdate) {
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

