package com.goudourasv.domain.courses;

import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.domain.lectures.LectureData;
import com.goudourasv.domain.tags.Tag;

import java.util.List;

public class LiveCourse {
    private String title;
    private Instructor instructor;
    private Institution institution;
    private List<Tag> tags;
    private LectureData liveLecture;

    public LiveCourse(String title,Instructor instructor,Institution institution,List<Tag> tags,LectureData liveLecture){
        this.title = title;
        this.instructor = instructor;
        this.institution = institution;
        this.tags = tags;
        this.liveLecture = liveLecture;


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

    public LectureData getLiveLecture() {
        return liveLecture;
    }

    public List<Tag> getTags() {
        return tags;
    }
}
