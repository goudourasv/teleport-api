package com.goudourasv.data.courses;

import com.goudourasv.data.institutions.InstitutionEntity;
import com.goudourasv.data.instructors.InstructorEntity;
import com.goudourasv.data.lectures.LectureEntity;
import com.goudourasv.data.tags.TagEntity;
import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.domain.tags.Tag;

import java.util.ArrayList;
import java.util.List;

public class CoursesMapper {
    public static Course mapCourseEntity(CourseEntity courseEntity) {
        InstitutionEntity institutionEntity = courseEntity.getInstitutionEntity();
        Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());

        InstructorEntity instructorEntity = courseEntity.getInstructorEntity();
        Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), new ArrayList<>());

        List<TagEntity> tagEntities = courseEntity.getTagEntities();
        List<Tag> tags = new ArrayList<>();
        for (TagEntity tagEntity : tagEntities) {
            Tag tag = new Tag(tagEntity.getName());
            tags.add(tag);
        }


        List<LectureEntity> lectureEntities = courseEntity.getLectureEntities();
        List<Lecture> lectures = new ArrayList<>();
        for (LectureEntity lectureEntity : lectureEntities) {
            Lecture lecture = new Lecture(lectureEntity.getId(), lectureEntity.getTitle(), null, lectureEntity.getStartTime(), lectureEntity.getEndTime());
            lectures.add(lecture);
        }

        Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, tags, instructor, courseEntity.getStartDate(), courseEntity.getEndDAte(), lectures);
        return course;

    }


    public static List<Course> mapCourseEntities(List<CourseEntity> courseEntities) {
        List<Course> filteredCourses = new ArrayList<>();

        for (CourseEntity courseEntity : courseEntities) {
            InstitutionEntity institutionEntity = courseEntity.getInstitutionEntity();
            Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());

            InstructorEntity instructorEntity = courseEntity.getInstructorEntity();
            Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), new ArrayList<>());

            List<TagEntity> tagEntities = courseEntity.getTagEntities();
            List<Tag> tags = new ArrayList<>();
            for (TagEntity tagEntity : tagEntities) {
                Tag tag = new Tag(tagEntity.getName());
                tags.add(tag);
            }

            List<LectureEntity> lectureEntities = courseEntity.getLectureEntities();
            List<Lecture> lectures = new ArrayList<>();
            for (LectureEntity lectureEntity : lectureEntities) {
                Lecture lecture = new Lecture(lectureEntity.getId(), lectureEntity.getTitle(), null, lectureEntity.getStartTime(), lectureEntity.getEndTime());
                lectures.add(lecture);
            }

            Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, tags, instructor, courseEntity.getStartDate(), courseEntity.getEndDAte(), lectures);
            filteredCourses.add(course);
        }
        return filteredCourses;

    }
}
