package com.goudourasv.domain.courses;

import java.util.UUID;

public class CourseLecture {
    private UUID courseIdOfLecture;
    private String courseTitleOfLecture;

    public CourseLecture(UUID CourseIdOfLecture, String courseTitleOfLecture) {
        this.courseIdOfLecture = CourseIdOfLecture;
        this.courseTitleOfLecture = courseTitleOfLecture;

    }

    public UUID getCourseIdOfLecture() {
        return courseIdOfLecture;
    }

    public String getCourseTitleOfLecture() {
        return courseTitleOfLecture;
    }

    public void setCourseIdOfLecture(UUID courseIdOfLecture) {
        this.courseIdOfLecture = courseIdOfLecture;
    }

    public void setCourseTitleOfLecture(String courseTitleOfLecture) {
        this.courseTitleOfLecture = courseTitleOfLecture;
    }
}
