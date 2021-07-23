package com.goudourasv.data.lectures;

import com.goudourasv.data.courses.CourseEntity;
import com.goudourasv.domain.courses.CourseData;
import com.goudourasv.domain.lectures.Lecture;

import java.util.ArrayList;
import java.util.List;

public class LecturesMapper {
    public static Lecture toLecture(LectureEntity lectureEntity, CourseEntity courseEntity) {
        CourseData course = new CourseData(courseEntity.getId(), courseEntity.getTitle());
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
    public static List<LectureEntity> toLectureEntities(List<Lecture> lectures){
        List<LectureEntity> lectureEntities = new ArrayList<>();
        for (Lecture lecture : lectures) {
            LectureEntity lectureEntity = new LectureEntity(lecture.getId(), lecture.getTitle(), lecture.getUri(), lecture.getStartTime(), lecture.getEndTime());
            lectureEntities.add(lectureEntity);
        }
        return lectureEntities;
    }
}
