package com.goudourasv.domain.lectures;

import org.hibernate.validator.constraints.URL;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;

public class LectureData {
    private UUID id;
    private String title;
    @URL
    private URI uri;
    private Instant startTime;
    private Instant endTime;

    public LectureData(UUID id, String title, URI uri, Instant startTime, Instant endTime) {
        this.id = id;
        this.title = title;
        this.uri = uri;
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

    public URI getUri() {
        return uri;
    }
}

