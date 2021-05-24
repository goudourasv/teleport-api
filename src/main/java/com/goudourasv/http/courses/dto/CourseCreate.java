package com.goudourasv.http.courses.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.tags.Tag;

import javax.validation.constraints.NotBlank;


public class CourseCreate {
    @NotBlank
    private String title;
    @JsonProperty("institution")
    @NotBlank
    private Institution institution;
    private Tag tag;
    @NotBlank
    private Instructor instructor;

    public CourseCreate(String title, Institution institution, Tag tag, Instructor instructor){
        this.title = title;
        this.institution = institution;
        this.tag = tag;
        this.instructor = instructor;
    }


    public String getTitle() {
        return title;
    }

    public Institution getInstitution() {
        return institution;
    }

    public Tag getTag() {
        return tag;
    }

    public Instructor getInstructor() {
        return instructor;
    }


}
