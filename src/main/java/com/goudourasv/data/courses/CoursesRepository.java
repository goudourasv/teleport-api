package com.goudourasv.data.courses;

import com.goudourasv.data.institutions.InstitutionEntity;
import com.goudourasv.data.instructors.InstructorEntity;
import com.goudourasv.data.lectures.LecturesRepository;
import com.goudourasv.data.tags.TagEntity;
import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.courses.LiveCourse;
import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.domain.lectures.LectureData;
import com.goudourasv.domain.tags.Tag;
import com.goudourasv.http.courses.dto.CourseCreate;
import com.goudourasv.http.courses.dto.CourseUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static com.goudourasv.data.courses.CoursesMapper.mapCourseEntities;
import static com.goudourasv.data.courses.CoursesMapper.mapCourseEntity;


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
        List<Course> courses = mapCourseEntities(courseEntities);
        return courses;
    }

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
                sqlQuery += "tags = :tags";
                isFirst = false;
            } else {
                sqlQuery += " AND tags = :tags";
            }
            parametersMap.put("tags", tags);
        }

        Query query = entityManager.createNativeQuery(sqlQuery, CourseEntity.class);

        for (String key : parametersMap.keySet()) {
            query.setParameter(key, parametersMap.get(key));
        }
        @SuppressWarnings("unchecked")
        List<CourseEntity> courseEntities = query.getResultList();
        List<Course> filteredCourses = mapCourseEntities(courseEntities);

        return filteredCourses;
    }

    public Course getSpecificCourse(UUID id) {
        CourseEntity courseEntity = entityManager.find(CourseEntity.class, id);
        if (courseEntity == null) {
            return null;
        }
        Course course = mapCourseEntity(courseEntity);
        return course;
    }

    public Course createCourse(CourseCreate courseCreate) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setTitle(courseCreate.getTitle());
        courseEntity.setStartDate(courseCreate.getStartDate());
        courseEntity.setEndDAte(courseCreate.getEndDate());
        //TODO check if institution(id) exists
        InstitutionEntity institutionEntity = entityManager.getReference(InstitutionEntity.class, courseCreate.getInstitutionId());
        courseEntity.setInstitutionEntity(institutionEntity);
        InstructorEntity instructorEntity = entityManager.getReference(InstructorEntity.class, courseCreate.getInstructorId());
        courseEntity.setInstructorEntity(instructorEntity);

        List<String> tags = courseCreate.getTags();
//        List<TagEntity> tagEntities = new ArrayList<>();
//        for (String tag : tags) {
//            TagEntity tagEntity = new TagEntity(tag);
//            tagEntities.add(tagEntity);
//        }
        List<TagEntity> tagEntities = tags.stream().map(TagEntity::new).collect(Collectors.toList());
        courseEntity.setTagEntities(tagEntities);

        entityManager.persist(courseEntity);
        entityManager.flush();
        Course course = mapCourseEntity(courseEntity);
        return course;
    }


    public boolean deleteSpecificCourse(UUID id) {
        String sqlQuery = "DELETE FROM courses WHERE id = :id ";
        int deletedEntities = entityManager.createNativeQuery(sqlQuery, CourseEntity.class).setParameter("id", id).executeUpdate();
        if (deletedEntities == 0) {
            return false;
        }
        return true;

    }

    public Course replaceCourse(CourseCreate courseCreate, UUID id) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setTitle(courseCreate.getTitle());
        courseEntity.setStartDate(courseCreate.getStartDate());
        courseEntity.setEndDAte(courseCreate.getEndDate());
        courseEntity.setId(id);

        InstitutionEntity institutionEntity = entityManager.getReference(InstitutionEntity.class, courseCreate.getInstitutionId());
        courseEntity.setInstitutionEntity(institutionEntity);
        InstructorEntity instructorEntity = entityManager.getReference(InstructorEntity.class, courseCreate.getInstructorId());
        courseEntity.setInstructorEntity(instructorEntity);

        List<String> tags = courseCreate.getTags();
        List<TagEntity> tagEntities = tags.stream().map(TagEntity::new).collect(Collectors.toList());
        courseEntity.setTagEntities(tagEntities);

        try {
            entityManager.merge(courseEntity);
        } catch (Exception ex) {
            throw new NotFoundException("Course with id: " + id + "doesn't exist");

        }
        Course course = mapCourseEntity(courseEntity);
        return course;

    }

    public Course partiallyUpdateCourse(CourseUpdate courseUpdate, UUID id) {
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

        Institution institution = null;

        if (courseUpdate.getInstitutionId() != null) {
            UUID newInstitutionId = courseUpdate.getInstitutionId();
            InstitutionEntity institutionEntity = entityManager.getReference(InstitutionEntity.class, newInstitutionId);
            courseEntity.setInstitutionEntity(institutionEntity);
            institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());

        }

        Instructor instructor = null;

        if (courseUpdate.getInstructorId() != null) {
            UUID newInstructorId = courseUpdate.getInstructorId();
            InstructorEntity instructorEntity = entityManager.getReference(InstructorEntity.class, newInstructorId);
            courseEntity.setInstructorEntity(instructorEntity);
            instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), new ArrayList<>());

        }

        List<Tag> tags = null;

        if (courseUpdate.getTags() != null) {
            List<String> newTags = courseUpdate.getTags();

            List<TagEntity> tagEntities = new ArrayList<>();
            for (String tag : newTags) {
                TagEntity tagEntity = new TagEntity(tag);
                tagEntities.add(tagEntity);
            }
            courseEntity.setTagEntities(tagEntities);
            tags = new ArrayList<>();
            for (TagEntity tagEntity : tagEntities) {
                Tag tag = new Tag(tagEntity.getName());
                tags.add(tag);
            }
        }

        entityManager.merge(courseEntity);
        entityManager.flush();

        Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, tags, instructor, courseEntity.getStartDate(), courseEntity.getEndDAte());
        return course;
    }

    public List<LiveCourse> getLiveCourses() {
        Instant currentTimestamp = Instant.now();
        List<CourseEntity> currentCourseEntities = entityManager.createNamedQuery("list_live_courses", CourseEntity.class)
                .setParameter("current_timestamp", currentTimestamp)
                .getResultList();
        List<Course> currentCourses = mapCourseEntities(currentCourseEntities);
        List<LiveCourse> liveCourses = getLiveEntities(currentCourses);

        return liveCourses;
    }

    private List<LiveCourse> getLiveEntities(List<Course> currentCourses) {
        List<LiveCourse> liveCourses = new ArrayList<>();
        for (Course course : currentCourses) {
            Lecture lecture = course.getLectures().get(0);
            LectureData lectureData = new LectureData(lecture.getId(), lecture.getTitle(), lecture.getStartTime(), lecture.getEndTime());
            LiveCourse liveCourse = new LiveCourse(course.getTitle(), course.getInstructor(), course.getInstitution(), course.getTags(), lectureData);
            liveCourses.add(liveCourse);
        }
        return liveCourses;
    }


}

