package com.goudourasv.data.institutions;

import com.goudourasv.data.courses.CourseEntity;
import com.goudourasv.domain.courses.Course;

import javax.persistence.*;
import java.util.List;
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
    @OneToMany(mappedBy="id",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CourseEntity> courseEntities;

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

    public List<CourseEntity> getCourseEntities() {
        return courseEntities;
    }
}
