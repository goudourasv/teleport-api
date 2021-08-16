package com.goudourasv.domain.courses;

import com.goudourasv.domain.institutions.InstitutionData;
import com.goudourasv.domain.instructors.InstructorData;
import com.goudourasv.domain.lectures.LectureData;
import com.goudourasv.domain.tags.Tag;

import java.util.Objects;
import java.util.Set;

public class LiveCourse {
    private String title;
    private InstructorData instructor;
    private InstitutionData institution;
    private Set<Tag> tags;
    private LectureData liveLecture;

    public LiveCourse(String title, InstructorData instructor, InstitutionData institution, Set<Tag> tags, LectureData liveLecture) {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInstructor(InstructorData instructor) {
        this.instructor = instructor;
    }

    public void setInstitution(InstitutionData institution) {
        this.institution = institution;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setLiveLecture(LectureData liveLecture) {
        this.liveLecture = liveLecture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LiveCourse that = (LiveCourse) o;
        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

}
