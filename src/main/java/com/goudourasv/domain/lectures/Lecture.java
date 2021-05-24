package com.goudourasv.domain.lectures;

import java.time.LocalDateTime;
import java.util.UUID;

public class Lecture {
    private UUID id;
    private String title;
    private LocalDateTime date;
    private LocalDateTime startΤime;
    private LocalDateTime endΤime;

    public Lecture(UUID id, String title, LocalDateTime date, LocalDateTime startΤime, LocalDateTime endΤime) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.startΤime = startΤime;
        this.endΤime = endΤime;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public LocalDateTime getEndΤime() {
        return endΤime;
    }

    public LocalDateTime getStartΤime() {
        return startΤime;
    }

    public void setStartΤime(LocalDateTime startΤime) {
        this.startΤime = startΤime;
    }

    public void setEndΤime(LocalDateTime endΤime) {
        this.endΤime = endΤime;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
