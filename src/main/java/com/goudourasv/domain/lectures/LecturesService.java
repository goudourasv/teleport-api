package com.goudourasv.domain.lectures;

import com.goudourasv.data.lectures.LecturesRepository;
import com.goudourasv.http.lectures.dto.LectureCreate;


import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LecturesService {
    private LecturesRepository lecturesRepository;

    public LecturesService(LecturesRepository lecturesRepository) {
        this.lecturesRepository = lecturesRepository;
    }

    @Transactional
    public List<Lecture> getFilteredLectures(UUID courseId) {
        List<Lecture> filteredLectures = lecturesRepository.getFilteredLectures(courseId);
        return filteredLectures;
    }

    @Transactional
    public Lecture getSpecificLecture(UUID lectureId) {
        Lecture lecture = lecturesRepository.getSpecificLecture(lectureId);
        return lecture;
    }

    @Transactional
    public Lecture createLecture(LectureCreate lectureCreate) {
        Lecture lecture = lecturesRepository.createLecture(lectureCreate);
        return lecture;
    }

    @Transactional
    public boolean deleteSpecificLecture(UUID id) {
        boolean deleted = lecturesRepository.deleteSpecificLecture(id);
        return deleted;
    }
}
