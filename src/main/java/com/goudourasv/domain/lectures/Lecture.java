package com.goudourasv.domain.lectures;

import java.time.Instant;
import java.util.UUID;

public class Lecture {
    private UUID id;
    private String title;
    private Instant date;
    private Instant startTime;
    private Instant endTime;

    public Lecture(UUID id, String title, Instant date, Instant startTime, Instant endTime) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Instant getDate() {
        return date;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Instant getStartTime() {
        return startTime;
    }


}
