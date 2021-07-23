package com.goudourasv.data.lectures;

import com.goudourasv.data.courses.CourseEntity;
import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.http.lectures.dto.LectureCreate;
import com.goudourasv.http.lectures.dto.LectureUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.goudourasv.data.lectures.LecturesMapper.toLecture;
import static com.goudourasv.data.lectures.LecturesMapper.toLectures;

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

        @SuppressWarnings("unchecked")
        List<LectureEntity> lectureEntities = query.getResultList();
        List<Lecture> lectures = toLectures(lectureEntities);

        return lectures;
    }

    public Lecture getSpecificLecture(UUID lectureId) {
        LectureEntity lectureEntity = entityManager.find(LectureEntity.class, lectureId);
        CourseEntity courseEntity = lectureEntity.getCourseEntity();
        Lecture lecture = toLecture(lectureEntity, courseEntity);
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

        Lecture lecture = toLecture(lectureEntity, courseEntity);
        return lecture;

    }

    public boolean deleteSpecificLecture(UUID id) {
        LectureEntity lectureEntity = entityManager.getReference(LectureEntity.class, id);
        if (lectureEntity == null) {
            return false;
        }
        entityManager.remove(lectureEntity);
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
        Lecture lecture = toLecture(lectureEntity, courseEntity);
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

        entityManager.merge(lectureEntity);
        entityManager.flush();
        CourseEntity courseEntity = lectureEntity.getCourseEntity();

        Lecture lecture = toLecture(lectureEntity, courseEntity);
        return lecture;


    }
}