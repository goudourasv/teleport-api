package com.goudourasv.data.tags;

import com.goudourasv.data.courses.CourseEntity;
import com.goudourasv.data.instructors.InstructorEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity(name = "Tags")
public class TagEntity {
    @Id
    private String name;

    @ManyToMany(mappedBy = "tagEntities")
    private List<CourseEntity> courseEntities;

    public TagEntity() {
    }

    public TagEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CourseEntity> getCourseEntities() {
        return courseEntities;
    }

    public void setCourseEntities(List<CourseEntity> courseEntities) {
        this.courseEntities = courseEntities;
    }
}
