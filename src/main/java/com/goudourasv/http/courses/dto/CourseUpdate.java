package com.goudourasv.http.courses.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;


public class CourseUpdate {
    private String title;
    @JsonProperty("institution")
    private UUID institutionId;
    private String tag;
    private UUID instructorId;

    public String getTitle() {
        return title;
    }

    public UUID getInstitutionId() {
        return institutionId;
    }

    public String getTag() {
        return tag;
    }

    public UUID getInstructorId() {
        return instructorId;
    }

}
