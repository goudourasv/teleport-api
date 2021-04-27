package com.goudourasv.courses.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;


public class CourseUpdate {
    private String title;
    @JsonProperty("institution")
    private String institutionName;
    private String tag;
    private String professor;

    public String getTitle() {
        return title;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getTag() {
        return tag;
    }

    public String getProfessor() {
        return professor;
    }

}
