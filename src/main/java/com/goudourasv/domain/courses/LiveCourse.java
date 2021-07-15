package com.goudourasv.domain.courses;

import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.domain.tags.Tag;

import java.util.List;

public class LiveCourse {
    private String title;
    private Instructor instructor;
    private Institution institution;
    private List<Tag> tags;
    private Lecture liveLectureTitle;

    public LiveCourse(String title,Instructor instructor,Institution institution,List<Tag> tags,Lecture liveLecture){
        this.title = title;
        this.instructor = instructor;
        this.institution = institution;
        this.tags = tags;
        this.liveLectureTitle = liveLecture;


    }


    public String getTitle() {
        return title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Institution getInstitution() {
        return institution;
    }

    public Lecture getLiveLecture() {
        return liveLectureTitle;
    }

    public List<Tag> getTags() {
        return tags;
    }
}
