package com.goudourasv.courses.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;


public class CourseCreate {
    @NotBlank
    private String title;
    @JsonProperty("institution")
    @NotBlank
    private String institutionName;
    private String tag;
    @NotBlank
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
