package com.goudourasv.data.instructors;

import com.goudourasv.data.courses.CourseEntity;
import com.goudourasv.data.institutions.InstitutionEntity;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "Instructors")
public class InstructorEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "institution_instructor",
            joinColumns = @JoinColumn (name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "institution_id"))
    private List<InstitutionEntity> institutionEntities;

    @OneToMany(mappedBy = "instructorEntity" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CourseEntity> courseEntities;

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<InstitutionEntity> getInstitutionEntities() {
        return institutionEntities;
    }

    public List<CourseEntity> getCourseEntities() {
        return courseEntities;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setInstitutionEntities(List<InstitutionEntity> institutionEntities) {
        this.institutionEntities = institutionEntities;
    }

    public void setCourseEntities(List<CourseEntity> courseEntities) {
        this.courseEntities = courseEntities;
    }
}
