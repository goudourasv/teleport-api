package com.goudourasv.data.courses;

import com.goudourasv.domain.courses.Course;
import com.goudourasv.http.courses.dto.CourseCreate;
import com.goudourasv.http.courses.dto.CourseUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import java.time.Instant;
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

    public boolean deleteSpecificCourse(UUID id) {
//        CourseEntity courseEntity = entityManager.getReference(CourseEntity.class,id);
//        if (courseEntity == null) {
//            return false;
//        }
//        entityManager.remove(courseEntity);
//        return true;

        String sqlQuery = "DELETE FROM courses WHERE id = :id ";
        entityManager.createNativeQuery(sqlQuery,CourseEntity.class).setParameter("id",id).executeUpdate();
        return true;

    }

    public Course replaceCourse(CourseCreate courseCreate, UUID id) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setTitle(courseCreate.getTitle());
        courseEntity.setStartDate(courseCreate.getStartDate());
        courseEntity.setEndDAte(courseCreate.getEndDate());
        courseEntity.setId(id);
        try {
            entityManager.merge(courseEntity);
        } catch (Exception ex) {
            throw new NotFoundException("Course with id: " + id + "doesn't exist");

        }
        Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), null, null, null, courseEntity.getStartDate(), courseEntity.getEndDAte());
        return course;

    }
    public Course partiallyUpdateCourse(CourseUpdate courseUpdate,UUID id) {
        CourseEntity courseEntity = entityManager.getReference(CourseEntity.class, id);

        if (courseUpdate.getTitle() != null) {
            String newTitle = courseUpdate.getTitle();
            courseEntity.setTitle(newTitle);
        }

        if (courseUpdate.getStartDate() != null) {
            Instant newStartDate = courseUpdate.getStartDate();
            courseEntity.setStartDate(newStartDate);
        }

        if (courseUpdate.getEndDate() != null) {
            Instant newEndDate = courseUpdate.getEndDate();
            courseEntity.setEndDAte(newEndDate);
        }

        entityManager.merge(courseEntity);
        entityManager.flush();

        Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), null, null, null, courseEntity.getStartDate(), courseEntity.getEndDAte());
        return course;
    }

}

