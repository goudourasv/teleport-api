package com.goudourasv.data.lectures;

import com.goudourasv.domain.lectures.Lecture;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class LecturesRepository {
    private final EntityManager entityManager;

    public LecturesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Lecture> getFilteredLectures() {
        String sqlQuery = "SELECT * FROM lectures";
        List<Lecture> lectures = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<LectureEntity> lectureEntities = entityManager.createNativeQuery(sqlQuery, LectureEntity.class).getResultList();
        for (LectureEntity lectureEntity : lectureEntities) {
            Lecture lecture = new Lecture(lectureEntity.getId(), lectureEntity.getTitle(), lectureEntity.getDate(), lectureEntity.getStartTime(), lectureEntity.getEndTime());
            lectures.add(lecture);
        }
        return lectures;
    }

}