package com.goudourasv.http.courses.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CourseUpdate {
    private String title;
    @JsonProperty("institution")
    private String institutionName;
    private String tag;
    private String instructor;

    public String getTitle() {
        return title;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getTag() {
        return tag;
    }

    public String getInstructor() {
        return instructor;
    }

}
