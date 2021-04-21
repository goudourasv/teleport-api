package com.goudourasv.lectures;

import java.time.Duration;
import java.time.LocalDateTime;

public class Lecture {
    private final String course;
    private final int  numberOfLecture;
    private LocalDateTime date;
    private Duration duration;


    //constructor
    public Lecture(String course, int num, LocalDateTime date,Duration duration) {
        this.course = course;
        numberOfLecture = num;
        this.date = date;
        this.duration = duration;

    }

    public String getLectureCourse() {
        return course;
    }

    public int getNumberOfLecture() {
        return numberOfLecture;
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
