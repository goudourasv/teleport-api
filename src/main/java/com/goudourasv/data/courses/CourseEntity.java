package com.goudourasv.data.courses;

import com.goudourasv.data.institutions.InstitutionEntity;
import com.goudourasv.data.instructors.InstructorEntity;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private InstitutionEntity institutionEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private InstructorEntity instructorEntity;



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

    public InstitutionEntity getInstitutionEntity() {
        return institutionEntity;
    }

    public InstructorEntity getInstructorEntity() {
        return instructorEntity;
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

    public void setInstitutionEntity(InstitutionEntity institutionEntity) {
        this.institutionEntity = institutionEntity;
    }

    public void setInstructorEntity(InstructorEntity instructorEntity) {
        this.instructorEntity = instructorEntity;
    }
}
