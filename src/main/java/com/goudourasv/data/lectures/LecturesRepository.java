package com.goudourasv.data.lectures;

import com.goudourasv.data.courses.CourseEntity;
import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.courses.CourseLecture;
import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.http.lectures.dto.LectureCreate;
import com.goudourasv.http.lectures.dto.LectureUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import java.time.Instant;
import java.util.*;

@ApplicationScoped
public class LecturesRepository {
    private final EntityManager entityManager;

    public LecturesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Lecture> getFilteredLectures(UUID courseId) {
        String sqlQuery = "SELECT * FROM lectures";
        Map<String, Object> parametersMap = new HashMap<>();

        if (courseId != null) {
            sqlQuery += " WHERE course_id = :courseId";
            parametersMap.put("courseId", courseId);
        }

        Query query = entityManager.createNativeQuery(sqlQuery, LectureEntity.class);
        for (String key : parametersMap.keySet()) {
            query.setParameter(key, parametersMap.get(key));
        }

        List<Lecture> lectures = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<LectureEntity> lectureEntities = query.getResultList();
        for (LectureEntity lectureEntity : lectureEntities) {
            CourseEntity courseEntity = lectureEntity.getCourseEntity();
            CourseLecture course = new CourseLecture(courseEntity.getId(), courseEntity.getTitle());
            Lecture lecture = new Lecture(lectureEntity.getId(), lectureEntity.getTitle(), course, lectureEntity.getStartTime(), lectureEntity.getEndTime());
            lectures.add(lecture);
        }
        return lectures;
    }

    public Lecture getSpecificLecture(UUID lectureId) {
        LectureEntity lectureEntity = entityManager.find(LectureEntity.class, lectureId);
        CourseEntity courseEntity = lectureEntity.getCourseEntity();
        CourseLecture course = new CourseLecture(courseEntity.getId(), courseEntity.getTitle());
        Lecture lecture = new Lecture(lectureId, lectureEntity.getTitle(), course, lectureEntity.getStartTime(), lectureEntity.getEndTime());
        return lecture;
    }

    public Lecture createLecture(LectureCreate lectureCreate) {
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setTitle(lectureCreate.getTitle());
        lectureEntity.setStartTime(lectureCreate.getStartTime());
        lectureEntity.setEndTime(lectureCreate.getEndTime());

        CourseEntity courseEntity = entityManager.getReference(CourseEntity.class, lectureCreate.getCourseId());
        lectureEntity.setCourseEntity(courseEntity);
        entityManager.persist(lectureEntity);
        entityManager.flush();

        CourseLecture course = new CourseLecture(courseEntity.getId(), courseEntity.getTitle());

        Lecture lecture = new Lecture(lectureEntity.getId(), lectureEntity.getTitle(), course, lectureEntity.getStartTime(), lectureEntity.getEndTime());
        return lecture;

    }

    public boolean deleteSpecificLecture(UUID id) {
        String sqlQuery = "DELETE FROM lectures WHERE id = :id ";
        int deletedEntities = entityManager.createNativeQuery(sqlQuery, LectureEntity.class).setParameter("id", id).executeUpdate();
        if (deletedEntities == 0) {
            return false;
        }
        return true;
    }

    public Lecture replaceLecture(UUID id, LectureCreate lectureCreate) {
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setTitle(lectureCreate.getTitle());
        lectureEntity.setStartTime(lectureCreate.getStartTime());
        lectureEntity.setEndTime(lectureCreate.getEndTime());
        lectureEntity.setId(id);

        CourseEntity courseEntity = entityManager.getReference(CourseEntity.class, lectureCreate.getCourseId());
        lectureEntity.setCourseEntity(courseEntity);
        try {
            entityManager.merge(lectureEntity);
        } catch (Exception ex) {
            throw new NotFoundException("Lecture with id: " + id + "doesn't exist");
        }
        CourseLecture course = new CourseLecture(courseEntity.getId(), courseEntity.getTitle());

        Lecture lecture = new Lecture(lectureEntity.getId(), lectureEntity.getTitle(), course, lectureEntity.getStartTime(), lectureEntity.getEndTime());
        return lecture;
    }

    public Lecture partiallyUpdateLecture(UUID id, LectureUpdate lectureUpdate) {
        LectureEntity lectureEntity = entityManager.getReference(LectureEntity.class, id);

        if (lectureUpdate.getTitle() != null) {
            String newLectureTitle = lectureUpdate.getTitle();
            lectureEntity.setTitle(newLectureTitle);
        }
        if (lectureUpdate.getStartTime() != null) {
            Instant newLectureStartTime = lectureUpdate.getStartTime();
            lectureEntity.setStartTime(newLectureStartTime);
        }
        if (lectureUpdate.getEndTime() != null) {
            Instant newLectureEndTime = lectureUpdate.getEndTime();
            lectureEntity.setEndTime(newLectureEndTime);
        }
        CourseLecture course = null;
        if (lectureUpdate.getCourseId() != null) {
            UUID newCourseId = lectureUpdate.getCourseId();
            CourseEntity courseEntity = entityManager.getReference(CourseEntity.class, newCourseId);
            lectureEntity.setCourseEntity(courseEntity);
            course = new CourseLecture(courseEntity.getId(), courseEntity.getTitle());

        }

        entityManager.merge(lectureEntity);
        entityManager.flush();

        Lecture lecture = new Lecture(lectureEntity.getId(), lectureEntity.getTitle(), course, lectureEntity.getStartTime(), lectureEntity.getEndTime());
        return lecture;


    }
}