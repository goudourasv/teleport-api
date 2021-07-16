package com.goudourasv.domain.lectures;

import java.time.Instant;
import java.util.UUID;

public class LectureData {
    private UUID id;
    private String title;
    private Instant startTime;
    private Instant endTime;

    public LectureData(UUID id, String title, Instant startTime, Instant endTime){
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public UUID getId() {
        return id;
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

