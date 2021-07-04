package com.goudourasv.domain.courses;

import java.util.UUID;

public class CourseLecture {
    private UUID courseLectureId;
    private String courseLectureTitle;

    public CourseLecture(UUID courseLectureId, String courseLectureTitle) {
        this.courseLectureId = courseLectureId;
        this.courseLectureTitle = courseLectureTitle;

    }

    public UUID getCourseLectureId() {
        return courseLectureId;
    }

    public String getCourseLectureTitle() {
        return courseLectureTitle;
    }

    public void setCourseLectureId(UUID courseLectureId) {
        this.courseLectureId = courseLectureId;
    }

    public void setCourseLectureTitle(String courseLectureTitle) {
        this.courseLectureTitle = courseLectureTitle;
    }
}
