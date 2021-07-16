package com.goudourasv.data.courses;

import com.goudourasv.data.institutions.InstitutionEntity;
import com.goudourasv.data.instructors.InstructorEntity;
import com.goudourasv.data.lectures.LectureEntity;
import com.goudourasv.data.tags.TagEntity;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

// TODO : check how to improve the fetching of the tags when running the below query
@NamedQueries({
        @NamedQuery(name = "list_live_courses",
                query = """
                         select c from Courses c
                         inner join fetch c.lectureEntities l
                         inner join fetch c.institutionEntity 
                         inner join fetch c.instructorEntity
                         where l.startTime <= :current_timestamp
                         and l.endTime > :current_timestamp
                        """)
})
@Entity(name = "Courses")
public class CourseEntity {
    @Id //Shows that id is the PK
    @GeneratedValue // Shows that it generated automatically
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

    @OneToMany(mappedBy = "courseEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureEntity> lectureEntities;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "course_tag",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "tag"))
    private List<TagEntity> tagEntities;


    public CourseEntity() {
    }

    public CourseEntity(UUID id, String title) {
        this.id = id;
        this.title = title;

    }


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

    public List<LectureEntity> getLectureEntities() {
        return lectureEntities;
    }

    public List<TagEntity> getTagEntities() {
        return tagEntities;
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

    public void setLectureEntities(List<LectureEntity> lectureEntities) {
        this.lectureEntities = lectureEntities;
    }

    public void setTagEntities(List<TagEntity> tagEntities) {
        this.tagEntities = tagEntities;
    }
}
