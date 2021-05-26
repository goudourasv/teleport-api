package com.goudourasv.http.courses.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.tags.Tag;

import javax.validation.constraints.NotBlank;
import java.time.Instant;


public class CourseCreate {
    @NotBlank
    private String title;
    @JsonProperty("institution")
    private Institution institution;
    private Tag tag;
    private Instructor instructor;
    private Instant startDate;
    private Instant endDate;

    public CourseCreate(String title, Institution institution, Tag tag, Instructor instructor, Instant startDate, Instant endDate) {
        this.title = title;
        this.institution = institution;
        this.tag = tag;
        this.instructor = instructor;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }
}
