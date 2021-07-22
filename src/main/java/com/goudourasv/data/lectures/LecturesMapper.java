package com.goudourasv.data.lectures;

import com.goudourasv.data.courses.CourseEntity;
import com.goudourasv.domain.courses.CourseLecture;
import com.goudourasv.domain.lectures.Lecture;

import java.util.ArrayList;
import java.util.List;

public class LecturesMapper {
    public static Lecture toLecture(LectureEntity lectureEntity, CourseEntity courseEntity) {
        CourseLecture course = new CourseLecture(courseEntity.getId(), courseEntity.getTitle());
        Lecture lecture = new Lecture(lectureEntity.getId(), lectureEntity.getTitle(), lectureEntity.getUri(), course, lectureEntity.getStartTime(), lectureEntity.getEndTime());
        return lecture;
    }

    public static List<Lecture> toLectures(List<LectureEntity> lectureEntities) {
        List<Lecture> lectures = new ArrayList<>();
        for (LectureEntity lectureEntity : lectureEntities) {
            CourseEntity courseEntity = lectureEntity.getCourseEntity();
           Lecture lecture = toLecture(lectureEntity,courseEntity);
            lectures.add(lecture);
        }
        return lectures;
    }
}
