package com.goudourasv.domain.courses;

import com.goudourasv.data.courses.CoursesRepository;
import com.goudourasv.domain.institutions.InstitutionsService;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.instructors.InstructorsService;
import com.goudourasv.domain.tags.Tag;
import com.goudourasv.domain.tags.TagsService;
import com.goudourasv.http.courses.dto.CourseCreate;
import com.goudourasv.http.courses.dto.CourseUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CoursesService {

    private InstitutionsService institutionsService;
    private InstructorsService instructorsService ;
    private TagsService tagsService;
    private CoursesRepository coursesRepository;

    public CoursesService(TagsService tagsService, CoursesRepository coursesRepository, InstitutionsService institutionsService,InstructorsService instructorsService) {
        this.tagsService = tagsService;
        this.coursesRepository = coursesRepository;
        this.institutionsService = institutionsService;
        this.instructorsService = instructorsService;
    }

    public List<Course> getCourses() {
        List<Course> courses = coursesRepository.getCourses();
        return courses;
    }

    //TODO check java Streams
    public List<Course> getFilteredList(List<Course> coursesList, String institution, String tag, String instructor) {
        List<Course> filteredList = new ArrayList<>();
        if (institution == null && tag == null && instructor == null) {
            filteredList.addAll(coursesList);
        } else {
            for (Course course : coursesList) {
//                boolean isMatch = true;
                if (instructor != null && !course.getInstructor().equals(instructor)) {
//                    isMatch = false;
                    continue;
                }
                if (institution != null && !course.getInstitution().equals(institution)) {
//                    isMatch = false;
                    continue;
                }
                if (tag != null && !course.getTag().equals(tag)) {
                    //                    isMatch = false;
                    continue;
                }
//                if (isMatch) {
                filteredList.add(course);
//                }
            }
        }
        return filteredList;
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
    public Course createNewCourse(CourseCreate courseCreate) {
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
        List<LiveCourse> liveCourses = new ArrayList<>();
        return liveCourses;


    }
}