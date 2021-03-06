package com.goudourasv.data.courses;

import com.goudourasv.data.institutions.InstitutionEntity;
import com.goudourasv.data.instructors.InstructorEntity;
import com.goudourasv.data.lectures.LectureEntity;
import com.goudourasv.data.tags.TagEntity;
import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.courses.LiveCourse;
import com.goudourasv.domain.institutions.InstitutionData;
import com.goudourasv.domain.instructors.InstructorData;
import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.domain.lectures.LectureData;
import com.goudourasv.domain.tags.Tag;
import com.goudourasv.http.courses.dto.CourseCreate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.goudourasv.data.lectures.LecturesMapper.toLectureEntities;

public class CoursesMapper {

    public static Course toCourse(CourseEntity courseEntity, boolean favourite) {
        InstitutionEntity institutionEntity = courseEntity.getInstitutionEntity();
        InstitutionData institution = new InstitutionData(institutionEntity.getId(), institutionEntity.getName());

        InstructorEntity instructorEntity = courseEntity.getInstructorEntity();
        InstructorData instructor = new InstructorData(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName());

        Set<TagEntity> tagEntities = courseEntity.getTagEntities();
        Set<Tag> tags = new HashSet<>();
        for (TagEntity tagEntity : tagEntities) {
            Tag tag = new Tag(tagEntity.getName());
            tags.add(tag);
        }

        List<LectureEntity> lectureEntities = courseEntity.getLectureEntities();
        List<LectureData> lectures = new ArrayList<>();
        for (LectureEntity lectureEntity : lectureEntities) {
            LectureData lecture = new LectureData(lectureEntity.getId(), lectureEntity.getTitle(), lectureEntity.getUri(), lectureEntity.getStartTime(), lectureEntity.getEndTime());
            lectures.add(lecture);
        }

        Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, tags, courseEntity.getStartDate(), courseEntity.getEndDate(), lectures, instructor);
        if (favourite) {
            course.setFavourite(true);
        }
        return course;
    }

    public static List<Course> toCourses(List<CourseEntity> courseEntities, boolean favourite) {
        List<Course> filteredCourses = new ArrayList<>();

        for (CourseEntity courseEntity : courseEntities) {
            InstitutionEntity institutionEntity = courseEntity.getInstitutionEntity();
            InstitutionData institution = new InstitutionData(institutionEntity.getId(), institutionEntity.getName());

            InstructorEntity instructorEntity = courseEntity.getInstructorEntity();
            InstructorData instructor = new InstructorData(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName());

            Set<TagEntity> tagEntities = courseEntity.getTagEntities();
            Set<Tag> tags = new HashSet<>();
            for (TagEntity tagEntity : tagEntities) {
                Tag tag = new Tag(tagEntity.getName());
                tags.add(tag);
            }

            List<LectureEntity> lectureEntities = courseEntity.getLectureEntities();
            List<LectureData> lectures = new ArrayList<>();
            for (LectureEntity lectureEntity : lectureEntities) {
                LectureData lecture = new LectureData(lectureEntity.getId(), lectureEntity.getTitle(), lectureEntity.getUri(), lectureEntity.getStartTime(), lectureEntity.getEndTime());
                lectures.add(lecture);
            }

            Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, tags, courseEntity.getStartDate(), courseEntity.getEndDate(), lectures, instructor);
            filteredCourses.add(course);
            if (favourite) {
                course.setFavourite(true);
            }
        }

        return filteredCourses;
    }

    public static CourseEntity toCourseEntity(CourseCreate courseCreate, InstitutionEntity institutionEntity, InstructorEntity instructorEntity) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setTitle(courseCreate.getTitle());
        courseEntity.setStartDate(courseCreate.getStartDate());
        courseEntity.setEndDate(courseCreate.getEndDate());
        courseEntity.setInstitutionEntity(institutionEntity);
        courseEntity.setInstructorEntity(instructorEntity);

        List<String> tags = courseCreate.getTags();
//        List<TagEntity> tagEntities = new ArrayList<>();
//        for (String tag : tags) {
//            TagEntity tagEntity = new TagEntity(tag);
//            tagEntities.add(tagEntity);
//        }
        Set<TagEntity> tagEntities = tags.stream().map(tag -> new TagEntity(tag)).collect(Collectors.toSet());
        courseEntity.setTagEntities(tagEntities);

        List<Lecture> lectures = courseCreate.getLectures();
        List<LectureEntity> lectureEntities = toLectureEntities(lectures);
        courseEntity.setLectureEntities(lectureEntities);

        return courseEntity;
    }

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
