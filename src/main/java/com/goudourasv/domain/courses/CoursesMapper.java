package com.goudourasv.domain.courses;

import com.goudourasv.domain.lectures.LectureData;
import com.goudourasv.domain.users.FavouriteCourse;
import com.goudourasv.domain.users.User;

import java.util.ArrayList;
import java.util.List;

public class CoursesMapper {
    public static List<LiveCourse> toLiveCourses(List<Course> currentCourses) {
        List<LiveCourse> liveCourses = new ArrayList<>();
        for (Course course : currentCourses) {
            // TODO: Reconsider this - if we keep it, we must check if the list has exactly one element, otherwise throw an error.
            LectureData lecture = course.getLectureData().get(0);
            LectureData lectureData = new LectureData(lecture.getId(), lecture.getTitle(), lecture.getUri(), lecture.getStartTime(), lecture.getEndTime());
            LiveCourse liveCourse = new LiveCourse(course.getTitle(), course.getInstructorData(), course.getInstitutionData(), course.getTags(), lectureData);
            liveCourses.add(liveCourse);
        }
        return liveCourses;
    }

}
