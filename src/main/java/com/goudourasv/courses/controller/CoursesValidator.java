package com.goudourasv.courses.controller;

import com.goudourasv.courses.domain.Course;

import javax.ws.rs.BadRequestException;

public class CoursesValidator {

    public static void validate(Course course) {
        //TODO validations for everything
        if (course.getTitle() == null) {
            throw new BadRequestException("Required property title missing");
        }

    }
}
