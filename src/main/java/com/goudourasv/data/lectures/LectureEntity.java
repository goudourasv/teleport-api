package com.goudourasv.data.lectures;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Entity(name = "Lectures")
public class LectureEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String title;
    @Column
    private Instant date;
    @Column(name = "start_time")
    private Instant startTime;
    @Column(name = "end_time")
    private Instant endTime;


    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Instant getDate() {
        return date;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }
}

