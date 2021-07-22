package com.goudourasv.domain.courses;

import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.institutions.InstitutionData;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.instructors.InstructorData;
import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.domain.lectures.LectureData;
import com.goudourasv.domain.tags.Tag;

import java.util.List;
import java.util.Set;

public class LiveCourse {
    private String title;
    private InstructorData instructor;
    private InstitutionData institution;
    private Set<Tag> tags;
    private LectureData liveLecture;

    public LiveCourse(String title,InstructorData instructor,InstitutionData institution,Set<Tag> tags,LectureData liveLecture){
        this.title = title;
        this.instructor = instructor;
        this.institution = institution;
        this.tags = tags;
        this.liveLecture = liveLecture;


    }


    public String getTitle() {
        return title;
    }

    public InstructorData getInstructor() {
        return instructor;
    }

    public InstitutionData getInstitution() {
        return institution;
    }

    public LectureData getLiveLecture() {
        return liveLecture;
    }

    public Set<Tag> getTags() {
        return tags;
    }
}
