package com.goudourasv.domain.lectures;

import java.time.Duration;
import java.time.LocalDateTime;

public class Lecture {
    private String title;
    private LocalDateTime date;
    private LocalDateTime start_time;
    private LocalDateTime end_time;


    //constructor
    public Lecture(String title, LocalDateTime date,LocalDateTime start_time,LocalDateTime end_time) {
        this.title = title;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;

    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }


    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
