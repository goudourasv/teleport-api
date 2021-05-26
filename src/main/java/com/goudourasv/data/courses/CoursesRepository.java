package com.goudourasv.data.courses;

import com.goudourasv.domain.courses.Course;
import com.goudourasv.http.courses.dto.CourseCreate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@ApplicationScoped
public class CoursesRepository {
    private final EntityManager entityManager;

    public CoursesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Course> getCourses() {
        String sqlQuery = "SELECT * FROM courses";
        List<Course> courses = new ArrayList<>();
        @SuppressWarnings("unchecked") // java Generics
        List<CourseEntity> courseEntities = entityManager.createNativeQuery(sqlQuery, CourseEntity.class).getResultList();
        for (CourseEntity courseEntity : courseEntities) {
            Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), null, null, null, courseEntity.getStartDate(), courseEntity.getEndDAte());
            courses.add(course);
        }
        return courses;
    }

    public Course createCourse(CourseCreate courseCreate) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setTitle(courseCreate.getTitle());
        courseEntity.setStartDate(courseCreate.getStartDate());
        courseEntity.setEndDAte(courseCreate.getEndDate());
        entityManager.persist(courseEntity);
        entityManager.flush();
        Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), null, null, null, courseEntity.getStartDate(), courseEntity.getEndDAte());
        return course;
    }

    public Course getSpecificCourse(UUID id) {

        CourseEntity courseEntity = entityManager.find(CourseEntity.class, id);
        if (courseEntity == null) {
            return null;
        }
        Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), null, null, null, courseEntity.getStartDate(), courseEntity.getEndDAte());
        return course;


    }
}

