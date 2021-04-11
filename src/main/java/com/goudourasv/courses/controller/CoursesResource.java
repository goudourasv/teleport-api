package com.goudourasv.courses.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goudourasv.courses.domain.Course;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Path("/courses")
public class CoursesResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //TODO Return courses matching an institution
    public List<Course> getCourses(@QueryParam("institution") String institution) {
        List<Course> coursesList = parseJsonFile();
        
        if (institution != null) {
            List<Course> filteredList = getFilteredByInstitutionList(coursesList, institution);
            return filteredList;
        } else {
            return coursesList;
        }
    }

    private List<Course> parseJsonFile() {
        List<Course> coursesList;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String file = Thread.currentThread().getContextClassLoader().getResource("/courses.json").getFile();
            coursesList = mapper.readValue(new File(file), new TypeReference<List<Course>>() {
            });
        } catch (Exception ex) {
            throw new ServerErrorException("Something went wrong", Response.Status.INTERNAL_SERVER_ERROR);
        }
        return coursesList;
    }

    private List<Course> getFilteredByInstitutionList(List<Course> coursesList, String institution) {
        List<Course> filteredByInstitutionList = new ArrayList<Course>();
        for (Course course : coursesList) {
            if (course.getInstitutionName().equals(institution)) {
                filteredByInstitutionList.add(course);
            }
        }
        return filteredByInstitutionList;
    }

//    //TODO Take from the same file a specific course from the list
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Course getCourse(@QueryParam("id") int id) {
//        Course myCourse = new Course();
//        List<Course> coursesList;
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            String file = Thread.currentThread().getContextClassLoader().getResource("/courses.json").getFile();
//            coursesList = mapper.readValue(new File(file), new TypeReference<List<Course>>() {
//            });
//        } catch (Exception ex) {
//            throw new ServerErrorException("Something went wrong", Response.Status.INTERNAL_SERVER_ERROR);
//        }
//
//        for (Course course : coursesList) {
//            if (course.getId()==id) {
//                myCourse = course;
//            }
//        }
//        return myCourse;
//    }

}

