package com.goudourasv.data.courses;

import com.goudourasv.data.institutions.InstitutionEntity;
import com.goudourasv.data.instructors.InstructorEntity;
import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.http.courses.dto.CourseCreate;
import com.goudourasv.http.courses.dto.CourseUpdate;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            InstitutionEntity institutionEntity = courseEntity.getInstitutionEntity();
            Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
            InstructorEntity instructorEntity = courseEntity.getInstructorEntity();
            Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), new ArrayList<>());

            Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, null, instructor, courseEntity.getStartDate(), courseEntity.getEndDAte());
            courses.add(course);
        }
        return courses;
    }

    public List<Course> getFilteredCourses(UUID institutionId, String tag, UUID instructorId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CourseEntity> criteria = builder.createQuery(CourseEntity.class);

        //get all items
        Root<CourseEntity> root = criteria.from(CourseEntity.class);
        criteria.select(root);

        boolean isFirst = true;
        if (institutionId != null) {
            if (isFirst) {
                criteria.where(builder.equal(root.get("institutionEntity"), institutionId.toString()));
                isFirst = false;
            }else {
                criteria.where(builder.and(builder.equal(root.get("institutionEntity"),institutionId.toString())));
            }
        }
        if (instructorId != null) {
            if (isFirst) {
                criteria.where(builder.equal(root.get("instructorEntity"), instructorId.toString()));
                isFirst = false;

            }else {
                criteria.where(builder.and(builder.equal(root.get("instructorEntity"),institutionId.toString())));
            }
        }
        @SuppressWarnings("unchecked")
        List<CourseEntity> courseEntities = entityManager.createQuery(criteria).getResultList();
        mapCourseEntities(courseEntities);
        List<Course> filteredCourses = new ArrayList<>();
        return filteredCourses;
    }
//    public List<Course> getFilteredCourses(UUID institutionId, String tag, UUID instructorId) {
//        String sqlQuery = "SELECT * FROM courses";
//        if (institutionId != null || tag != null || instructorId != null) {
//            sqlQuery += " WHERE ";
//        }
//
//        Map<String, Object> parametersMap = new HashMap<>();
//
//        boolean isFirst = true;
//        if (institutionId != null) {
//            if (isFirst) {
//                sqlQuery += "institution_id =: institutionId";
//                isFirst = false;
//            } else {
//                sqlQuery += " AND institution_id =: institutionId";
//            }
//            parametersMap.put("institutionId", institutionId);
//        }
//        if (instructorId != null) {
//            if (isFirst) {
//                sqlQuery += "instructor_id =: instructorId";
//                isFirst = false;
//            } else {
//                sqlQuery += " AND instructor_id =: instructorId";
//            }
//            parametersMap.put("instructorId", instructorId);
//        }
//
//        Query query = entityManager.createNativeQuery(sqlQuery, CourseEntity.class);
//
//        for (String key : parametersMap.keySet()) {
//            query.setParameter(key, parametersMap.get(key));
//        }
//        @SuppressWarnings("unchecked")
//        List<CourseEntity> courseEntities = query.getResultList();
//        List<Course> filteredCourses = mapCourseEntities(courseEntities);
//
//        return filteredCourses;
//    }

    private List<Course> mapCourseEntities(List<CourseEntity> courseEntities) {
        List<Course> filteredCourses = new ArrayList<>();

        for (CourseEntity courseEntity : courseEntities) {
            InstitutionEntity institutionEntity = courseEntity.getInstitutionEntity();
            Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
            InstructorEntity instructorEntity = courseEntity.getInstructorEntity();
            Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), new ArrayList<>());

            Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, null, instructor, courseEntity.getStartDate(), courseEntity.getEndDAte());
            filteredCourses.add(course);
        }
        return filteredCourses;
    }

//    public List<Course> getFilteredCourses(List<Course> courseList,UUID institutionId, String tag, UUID instructorId) {
//        List<Course> filteredCourses = new ArrayList<>();
//        String sqlQuery = "";
//        if (institutionId == null && tag == null && instructorId == null) {
//            sqlQuery = "SELECT * FROM courses";
//            @SuppressWarnings("unchecked")//java generics
//            List<CourseEntity> courseEntities = entityManager.createNativeQuery(sqlQuery, CourseEntity.class).getResultList();
//            for (CourseEntity courseEntity : courseEntities) {
//                InstitutionEntity institutionEntity = courseEntity.getInstitutionEntity();
//                Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
//                InstructorEntity instructorEntity = courseEntity.getInstructorEntity();
//                Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), new ArrayList<>());
//
//                Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, null, instructor, courseEntity.getStartDate(), courseEntity.getEndDAte());
//                filteredCourses.add(course);
//            }
//            return filteredCourses;
//        } else {
//            if (institutionId != null) {
//                sqlQuery = "SELECT * FROM courses WHERE institution_id = :institutionId";
//                @SuppressWarnings("unchecked")
//                List<CourseEntity> courseEntities = entityManager.createNativeQuery(sqlQuery, CourseEntity.class).setParameter("institutionId", institutionId).getResultList();
//                for (CourseEntity courseEntity : courseEntities) {
//                    InstitutionEntity institutionEntity = courseEntity.getInstitutionEntity();
//                    Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
//                    InstructorEntity instructorEntity = courseEntity.getInstructorEntity();
//                    Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), new ArrayList<>());
//
//                    Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, null, instructor, courseEntity.getStartDate(), courseEntity.getEndDAte());
//                    filteredCourses.add(course);
//                }
//            } else {
//                if (instructorId != null) {
//                    sqlQuery = "SELECT * FROM courses WHERE instructor_id = :instructorId";
//                    @SuppressWarnings("unchecked")
//                    List<CourseEntity> courseEntities = entityManager.createNativeQuery(sqlQuery, CourseEntity.class).setParameter("instructorId", instructorId).getResultList();
//                    for (CourseEntity courseEntity : courseEntities) {
//                        InstitutionEntity institutionEntity = courseEntity.getInstitutionEntity();
//                        Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
//                        InstructorEntity instructorEntity = courseEntity.getInstructorEntity();
//                        Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), new ArrayList<>());
//
//                        Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, null, instructor, courseEntity.getStartDate(), courseEntity.getEndDAte());
//                        filteredCourses.add(course);
//                    }
//                } else {
//                    if (tag != null) {
//                        sqlQuery = "SELECT * FROM courses WHERE tag = :tag";
//                        @SuppressWarnings("unchecked")
//                        List<CourseEntity> courseEntities = entityManager.createNativeQuery(sqlQuery, CourseEntity.class).setParameter("tag", tag).getResultList();
//                        for (CourseEntity courseEntity : courseEntities) {
//                            InstitutionEntity institutionEntity = courseEntity.getInstitutionEntity();
//                            Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
//                            InstructorEntity instructorEntity = courseEntity.getInstructorEntity();
//                            Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), new ArrayList<>());
//
//                            Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, null, instructor, courseEntity.getStartDate(), courseEntity.getEndDAte());
//                            filteredCourses.add(course);
//                        }
//                    }
//                }
//            }
//        }
//        return filteredCourses;
//    }

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

        entityManager.persist(courseEntity);
        entityManager.flush();

        Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
        Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), new ArrayList<>());

        Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, null, instructor, courseEntity.getStartDate(), courseEntity.getEndDAte());
        return course;
    }

    public Course getSpecificCourse(UUID id) {
        CourseEntity courseEntity = entityManager.find(CourseEntity.class, id);
        if (courseEntity == null) {
            return null;
        }
        InstitutionEntity institutionEntity = courseEntity.getInstitutionEntity();
        Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
        InstructorEntity instructorEntity = courseEntity.getInstructorEntity();
        Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), new ArrayList<>());

        Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, null, instructor, courseEntity.getStartDate(), courseEntity.getEndDAte());
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
        try {
            entityManager.merge(courseEntity);
        } catch (Exception ex) {
            throw new NotFoundException("Course with id: " + id + "doesn't exist");

        }
        Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
        Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), new ArrayList<>());

        Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, null, instructor, courseEntity.getStartDate(), courseEntity.getEndDAte());
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

        entityManager.merge(courseEntity);
        entityManager.flush();

        Course course = new Course(courseEntity.getId(), courseEntity.getTitle(), institution, null, instructor, courseEntity.getStartDate(), courseEntity.getEndDAte());
        return course;
    }

}

