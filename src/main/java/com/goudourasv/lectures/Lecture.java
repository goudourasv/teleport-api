package com.goudourasv.lectures;

import java.time.Duration;
import java.time.LocalDateTime;

public class Lecture {
    private String title;
    private LocalDateTime date;
    private Duration duration;


    //constructor
    public Lecture(String title, LocalDateTime date, Duration duration) {
        this.title = title;
        this.date = date;
        this.duration = duration;

    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
