package com.goudourasv.domain.lectures;

import com.goudourasv.data.lectures.LecturesRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class LecturesService {
    private LecturesRepository lecturesRepository;

    public LecturesService(LecturesRepository lecturesRepository) {
        this.lecturesRepository = lecturesRepository;
    }

    @Transactional
    public List<Lecture> getFilteredLectures() {
        List<Lecture> filteredLectures = lecturesRepository.getFilteredLectures();
        return filteredLectures;
    }
}
