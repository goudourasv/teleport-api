package com.goudourasv.data.lectures;

import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.http.lectures.dto.LectureCreate;
import com.goudourasv.http.lectures.dto.LectureUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LecturesRepository {
    private final EntityManager entityManager;

    public LecturesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Lecture> getFilteredLectures(UUID courseId) {
        String sqlQuery = "SELECT * FROM lectures";
        List<Lecture> lectures = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<LectureEntity> lectureEntities = entityManager.createNativeQuery(sqlQuery, LectureEntity.class).getResultList();
        for (LectureEntity lectureEntity : lectureEntities) {
            Lecture lecture = new Lecture(lectureEntity.getId(), lectureEntity.getTitle(), null, lectureEntity.getStartTime(), lectureEntity.getEndTime());
            lectures.add(lecture);
        }
        return lectures;
    }

    public Lecture getSpecificLecture(UUID lectureId) {
        LectureEntity lectureEntity = entityManager.find(LectureEntity.class, lectureId);
        Lecture lecture = new Lecture(lectureId, lectureEntity.getTitle(), null, lectureEntity.getStartTime(), lectureEntity.getEndTime());
        return lecture;
    }

    public Lecture createLecture(LectureCreate lectureCreate) {
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setTitle(lectureCreate.getTitle());
        lectureEntity.setStartTime(lectureCreate.getStartTime());
        lectureEntity.setEndTime(lectureCreate.getEndTime());

        entityManager.persist(lectureEntity);
        entityManager.flush();

        Lecture lecture = new Lecture(lectureEntity.getId(), lectureEntity.getTitle(), null, lectureEntity.getStartTime(), lectureEntity.getEndTime());
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

    public Lecture replaceLecture(UUID id, LectureUpdate lectureUpdate) {
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setTitle(lectureUpdate.getTitle());
        lectureEntity.setStartTime(lectureUpdate.getStartTime());
        lectureEntity.setEndTime(lectureUpdate.getEndTime());
        lectureEntity.setId(id);
        try {
            entityManager.merge(lectureEntity);
        } catch (Exception ex) {
            throw new NotFoundException("Lecture with id: " + id + "doesn't exist");
        }

        Lecture lecture = new Lecture(lectureEntity.getId(), lectureEntity.getTitle(), null, lectureEntity.getStartTime(), lectureEntity.getEndTime());
        return lecture;
    }
}