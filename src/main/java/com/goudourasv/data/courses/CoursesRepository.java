package com.goudourasv.data.courses;

import com.goudourasv.data.institutions.InstitutionEntity;
import com.goudourasv.data.instructors.InstructorEntity;
import com.goudourasv.data.lectures.LectureEntity;
import com.goudourasv.data.lectures.LecturesMapper;
import com.goudourasv.data.lectures.LecturesRepository;
import com.goudourasv.data.tags.TagEntity;
import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.courses.LiveCourse;
import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.http.courses.dto.CourseCreate;
import com.goudourasv.http.courses.dto.CourseUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.time.Instant;
import java.util.*;

import static com.goudourasv.data.courses.CoursesMapper.*;
import static com.goudourasv.domain.courses.CoursesMapper.toLiveCourses;


@ApplicationScoped
public class CoursesRepository {
    private final EntityManager entityManager;
    private final LecturesRepository lecturesRepository;

    public CoursesRepository(EntityManager entityManager, LecturesRepository lecturesRepository) {
        this.entityManager = entityManager;
        this.lecturesRepository = lecturesRepository;
    }

    public List<Course> getCourses() {
        String sqlQuery = "SELECT * FROM courses";

        @SuppressWarnings("unchecked") // java Generics
        List<CourseEntity> courseEntities = entityManager.createNativeQuery(sqlQuery, CourseEntity.class).getResultList();
        List<Course> courses = toCourses(courseEntities);
        return courses;
    }

    //TODO: implement filtering with namedQuery
    public List<Course> getFilteredCourses(UUID institutionId, List<String> tags, UUID instructorId) {
        String sqlQuery = "SELECT * FROM courses";
        if (!tags.isEmpty()) {
            sqlQuery += " JOIN course_tag ON courses.id = course_tag.course_id";
        }
        if (institutionId != null || instructorId != null || !tags.isEmpty()) {

            sqlQuery += " WHERE ";
        }

        Map<String, Object> parametersMap = new HashMap<>();

        boolean isFirst = true;
        if (institutionId != null) {
            if (isFirst) {
                sqlQuery += "institution_id = :institutionId";
                isFirst = false;
            } else {
                sqlQuery += " AND institution_id = :institutionId";
            }
            parametersMap.put("institutionId", institutionId);
        }

        if (instructorId != null) {
            if (isFirst) {
                sqlQuery += "instructor_id = :instructorId";
                isFirst = false;
            } else {
                sqlQuery += " AND instructor_id = :instructorId";
            }
            parametersMap.put("instructorId", instructorId);
        }

        if (!tags.isEmpty()) {
            if (isFirst) {
                sqlQuery += "tag = :tag";
                isFirst = false;
            } else {
                sqlQuery += " AND tag = :tag";
            }
            parametersMap.put("tag", tags);
        }

        Query query = entityManager.createNativeQuery(sqlQuery, CourseEntity.class);

        for (String key : parametersMap.keySet()) {
            query.setParameter(key, parametersMap.get(key));
        }

        @SuppressWarnings("unchecked")
        List<CourseEntity> courseEntities = query.getResultList();
        List<Course> filteredCourses = toCourses(courseEntities);

        return filteredCourses;
    }

    public Course getSpecificCourse(UUID id) {
        CourseEntity courseEntity = entityManager.find(CourseEntity.class, id);
        if (courseEntity == null) {
            return null;
        }
        Course course = toCourse(courseEntity);
        return course;
    }

    private InstitutionEntity findInstitutionById(UUID id) {
        try {
            InstitutionEntity institutionEntity = entityManager.getReference(InstitutionEntity.class, id);
            return institutionEntity;
        } catch (Exception e) {
            throw new BadRequestException("Institution with id " + id + " doesn't exist");
        }
    }

    private InstructorEntity findInstructorById(UUID id) {
        try {
            InstructorEntity instructorEntity = entityManager.getReference(InstructorEntity.class, id);
            return instructorEntity;
        } catch (Exception e) {
            throw new BadRequestException("Instructor with id " + id + " doesn't exist");
        }
    }

    public Course createCourse(CourseCreate courseCreate) {
        InstitutionEntity institutionEntity = findInstitutionById(courseCreate.getInstitutionId());
        InstructorEntity instructorEntity = findInstructorById(courseCreate.getInstructorId());
        CourseEntity courseEntity = toCourseEntity(courseCreate, institutionEntity, instructorEntity);

        entityManager.persist(courseEntity);
        entityManager.flush();

        Course course = toCourse(courseEntity);
        return course;
    }

    public boolean deleteSpecificCourse(UUID id) {
        CourseEntity courseEntity = entityManager.getReference(CourseEntity.class, id);
        if (courseEntity == null) {
            return false;
        }
        entityManager.remove(courseEntity);
        return true;
    }

    public Course replaceCourse(CourseCreate courseCreate, UUID id) {
        InstitutionEntity institutionEntity = findInstitutionById(courseCreate.getInstitutionId());
        InstructorEntity instructorEntity = findInstructorById(courseCreate.getInstructorId());
        CourseEntity courseEntity = toCourseEntity(courseCreate, institutionEntity, instructorEntity);
        courseEntity.setId(id);

        try {
            entityManager.merge(courseEntity);
        } catch (Exception ex) {
            throw new NotFoundException("Course with id: " + id + "doesn't exist");
        }

        Course course = toCourse(courseEntity);
        return course;

    }

    public Course partiallyUpdateCourse(CourseUpdate courseUpdate, UUID id) {
        CourseEntity courseEntity = entityManager.find(CourseEntity.class, id);

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
            courseEntity.setEndDate(newEndDate);
        }

        if (courseUpdate.getInstitutionId() != null) {
            UUID newInstitutionId = courseUpdate.getInstitutionId();
            InstitutionEntity institutionEntity = entityManager.getReference(InstitutionEntity.class, newInstitutionId);
            courseEntity.setInstitutionEntity(institutionEntity);
        }

        if (courseUpdate.getInstructorId() != null) {
            UUID newInstructorId = courseUpdate.getInstructorId();
            InstructorEntity instructorEntity = entityManager.getReference(InstructorEntity.class, newInstructorId);
            courseEntity.setInstructorEntity(instructorEntity);
        }

        if (courseUpdate.getTags() != null) {
            List<String> newTags = courseUpdate.getTags();

            Set<TagEntity> tagEntities = new HashSet<>();
            for (String tag : newTags) {
                TagEntity tagEntity = new TagEntity(tag);
                tagEntities.add(tagEntity);
            }
            courseEntity.setTagEntities(tagEntities);
        }

        if (courseUpdate.getLectures() != null) {
            List<Lecture> lecturesToUpdate = courseUpdate.getLectures();
            List<LectureEntity> lectureEntities = LecturesMapper.toLectureEntities(lecturesToUpdate);
            courseEntity.setLectureEntities(lectureEntities);
        }

        entityManager.merge(courseEntity);
        entityManager.flush();
        return toCourse(courseEntity);
    }

    public List<LiveCourse> getLiveCourses() {
        Instant currentTimestamp = Instant.now();
        List<CourseEntity> currentCourseEntities = entityManager.createNamedQuery("list_live_courses", CourseEntity.class)
                .setParameter("current_timestamp", currentTimestamp)
                .getResultList();
        List<Course> currentCourses = toCourses(currentCourseEntities);
        List<LiveCourse> liveCourses = toLiveCourses(currentCourses);

        return liveCourses;
    }
}

