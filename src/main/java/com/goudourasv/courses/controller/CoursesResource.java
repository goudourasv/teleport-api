package com.goudourasv.courses.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goudourasv.courses.domain.Course;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.List;


@Path("/courses")
public class CoursesResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses() {
        List<Course> coursesList = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String file = Thread.currentThread().getContextClassLoader().getResource("/courses.json").getFile();
            coursesList = mapper.readValue(new File(file), new TypeReference<List<Course>>() {
            });
        } catch (Exception ex) {
            //TODO return 500 error
            return null;
        }
        return coursesList;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Course getCourse(){

    }

}


