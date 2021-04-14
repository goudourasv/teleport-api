package com.goudourasv.courses.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getCourse(@PathParam("id") int id) {
        Course course = new Course();
        List<Course> coursesList = parseJsonFile();

        for (Course element : coursesList) {
            if (element.getId() == id) {
                course = element;
            }
        }
        return course;
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course createCourse(Course input) {
        try {
            Course createdCourse = coursesService.createNewCourse(input) ;
            return createdCourse;

        } catch (Exception ex) {
            throw new ClientErrorException(Response.Status.EXPECTATION_FAILED);
        }
    }

}

