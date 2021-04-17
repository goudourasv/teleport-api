package com.goudourasv.courses.controller;

import com.goudourasv.courses.domain.Course;

import javax.ws.rs.BadRequestException;

public class CoursesValidator {
//TODO validation with annotations

    public static void validate(Course course) {
        if (course.getId() <= 0) {
            throw new BadRequestException("Invalid course id");
        }
        if (course.getTitle() == null || course.getTitle().equals("")) {
            throw new BadRequestException("Required property title missing");
        }

        if (course.getProfessor() == null || course.getProfessor().equals("")) {
            throw new BadRequestException("Required property professor missing");
        }

        if (course.getInstitutionName() == null || course.getInstitutionName().equals("")) {
            throw new BadRequestException("Required property institution missing");
        }

//        if (course.getStartDate().isBefore()) {
//            throw new BadRequestException("Property StartDate of the course " + course.getStartDate() + " has passed");
//        }
    }
}
