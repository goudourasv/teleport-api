package com.goudourasv.http.courses.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.tags.Tag;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.UUID;


public class CourseCreate {
    @NotBlank
    private String title;
    @JsonProperty("institution")
    private UUID institutionId;
    private Tag tag;
    private Instructor instructor;
    private Instant startDate;
    private Instant endDate;

    public CourseCreate(String title, UUID institutionId, Tag tag, Instructor instructor, Instant startDate, Instant endDate) {
        this.title = title;
        this.institutionId = institutionId;
        this.tag = tag;
        this.instructor = instructor;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public String getTitle() {
        return title;
    }

    public UUID getInstitutionId() {
        return institutionId;
    }

    public Tag getTag() {
        return tag;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }


}
