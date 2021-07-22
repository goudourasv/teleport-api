package com.goudourasv.http.courses.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.domain.tags.Tag;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CourseUpdate {
    private String title;
    private Instant startDate;
    private Instant endDate;
    @JsonProperty("institution")
    private UUID institutionId;
    private List<String> tags = new ArrayList<>();
    @JsonProperty("instructor")
    private UUID instructorId;
    @JsonProperty("lectures")
    private List<Lecture> lectures;


    public CourseUpdate(String title, UUID institutionId, List<String> tags, UUID instructorId, Instant startDate, Instant endDate,List<Lecture> lectures) {
        this.title = title;
        this.institutionId = institutionId;
        if(tags != null) {
            this.tags = tags;
        }
        this.instructorId = instructorId;
        this.startDate = startDate;
        this.endDate = endDate;
        if(lectures != null){
        this.lectures = lectures;
        }
    }

    public String getTitle() {
        return title;
    }

    public UUID getInstitutionId() {
        return institutionId;
    }

    public List<String> getTags() {
        return tags;
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

    public List<Lecture> getLectures() {
        return lectures;
    }
}
