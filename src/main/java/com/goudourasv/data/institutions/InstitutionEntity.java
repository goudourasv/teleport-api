package com.goudourasv.data.institutions;

import com.goudourasv.data.courses.CourseEntity;
import com.goudourasv.data.instructors.InstructorEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Institutions")
public class InstitutionEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String name;
    @Column
    private String city;
    @Column
    private String country;

    @OneToMany(mappedBy="institutionEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CourseEntity> courseEntities;

    @ManyToMany(mappedBy = "institutionEntities")
    private  List<InstructorEntity> instructorEntities;



    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public List<CourseEntity> getCourseEntities() {
        return courseEntities;
    }

    public List<InstructorEntity> getInstructorEntities() {
        return instructorEntities;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCourseEntities(List<CourseEntity> courseEntities) {
        this.courseEntities = courseEntities;
    }

    public void setInstructorEntities(List<InstructorEntity> instructorEntities) {
        this.instructorEntities = instructorEntities;
    }
}
