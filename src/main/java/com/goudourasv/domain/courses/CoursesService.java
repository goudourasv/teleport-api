package com.goudourasv.domain.courses;

import com.goudourasv.data.courses.CoursesRepository;
import com.goudourasv.http.courses.dto.CourseCreate;
import com.goudourasv.http.courses.dto.CourseUpdate;
import com.goudourasv.http.users.dto.FavouriteCourseCreate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class CoursesService {


    // TODO: Use an interface to invert this dependency (we want the repository to depend on the service)
    private CoursesRepository coursesRepository;

    public CoursesService(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    // TODO: check java Streams
    public List<Course> getFilteredCourses(UUID institutionId, Set<String> tags, UUID instructorId) {
        List<Course> filteredCourses = coursesRepository.getFilteredCourses(institutionId, tags, instructorId);
        return filteredCourses;
    }

    @Transactional
    public Course getSpecificCourse(UUID id) {
        Course specificCourse = coursesRepository.getSpecificCourse(id);
        return specificCourse;
    }

    @Transactional
    public boolean deleteSpecificCourse(UUID id) {
        boolean deleted = coursesRepository.deleteSpecificCourse(id);
        return deleted;
    }

    @Transactional
    public Course replaceCourse(CourseCreate courseCreate, UUID id) {
        //TODO Handle startDate and endDate
        Course updatedCourse = coursesRepository.replaceCourse(courseCreate, id);
        return updatedCourse;
    }

    @Transactional
    public Course createCourse(CourseCreate courseCreate) {
        Course course = coursesRepository.createCourse(courseCreate);
        return course;
    }

    @Transactional
    public Course partiallyUpdateCourse(CourseUpdate courseUpdate, UUID id) {
        Course updatedCourse = coursesRepository.partiallyUpdateCourse(courseUpdate, id);
        return updatedCourse;
    }

    //TODO live course list
    public List<LiveCourse> getLiveCourses() {
        List<LiveCourse> liveCourses = coursesRepository.getLiveCourses();
        return liveCourses;
    }

    @Transactional
    public Course createFavourite(FavouriteCourseCreate favouriteCourseCreate) {
        Course favouriteCourse = coursesRepository.createFavouriteCourse(favouriteCourseCreate);
        return favouriteCourse;
    }

    @Transactional
    public boolean deleteSpecificFavouriteCourse(UUID userId, UUID courseId) {
        boolean deleted = coursesRepository.deleteSpecificFavourite(userId, courseId);
        return deleted;
    }

    @Transactional
    public List<Course> getFavouriteCourses(UUID userId) {
        List<Course> favouriteCourses = coursesRepository.getFavouriteCourses(userId);
        return favouriteCourses;
    }
}
