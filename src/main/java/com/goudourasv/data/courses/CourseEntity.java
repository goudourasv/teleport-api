package com.goudourasv.data.courses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Entity(name = "Courses")
public class CourseEntity {
    @Id //Shows that id is the PK
    @GeneratedValue // Shows tha it generated automatically
    private UUID id;
    @Column
    private String title;
    @Column(name = "start_date")
    private Instant startDate;
    @Column(name = "end_date")
    private Instant endDAte;

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDAte() {
        return endDAte;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public void setEndDAte(Instant endDAte) {
        this.endDAte = endDAte;
    }
}
