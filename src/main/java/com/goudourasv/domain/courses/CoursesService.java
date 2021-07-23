package com.goudourasv.domain.courses;

import com.goudourasv.data.courses.CoursesRepository;
import com.goudourasv.domain.institutions.InstitutionsService;
import com.goudourasv.domain.instructors.InstructorsService;
import com.goudourasv.domain.tags.TagsService;
import com.goudourasv.http.courses.dto.CourseCreate;
import com.goudourasv.http.courses.dto.CourseUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CoursesService {

    private InstitutionsService institutionsService;
    private InstructorsService instructorsService;
    private TagsService tagsService;
    // TODO: Use an interface to invert this dependency (we want the repository to depend on the service)
    private CoursesRepository coursesRepository;

    public CoursesService(TagsService tagsService, CoursesRepository coursesRepository, InstitutionsService institutionsService, InstructorsService instructorsService) {
        this.tagsService = tagsService;
        this.coursesRepository = coursesRepository;
        this.institutionsService = institutionsService;
        this.instructorsService = instructorsService;
    }

    // TODO: check java Streams
    public List<Course> getFilteredCourses(UUID institutionId, List<String> tags, UUID instructorId) {
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
}