package com.goudourasv.http.courses.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goudourasv.domain.tags.Tag;

import java.time.Instant;
import java.util.UUID;


public class CourseUpdate {
    private String title;
    @JsonProperty("institution")
    private UUID institutionId;
    private String tag;
    @JsonProperty("instructor")
    private UUID instructorId;
    private Instant startDate;
    private Instant endDate;

    public CourseUpdate(String title, UUID institutionId, String tag, UUID instructorId, Instant startDate, Instant endDate) {
        this.title = title;
        this.institutionId = institutionId;
        this.tag = tag;
        this.instructorId = instructorId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

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

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }
}
