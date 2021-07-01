package com.goudourasv.http.lectures.dto;

import com.goudourasv.domain.lectures.Lecture;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

public class LectureCreate {
    @NotBlank
    private String title;
    private Instant startTime;
    private Instant endTime;

    public LectureCreate(String title,Instant startTime,Instant endTime){
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

}
