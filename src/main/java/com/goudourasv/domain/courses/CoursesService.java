package com.goudourasv.domain.courses;

import com.goudourasv.http.courses.dto.CourseCreate;
import com.goudourasv.http.courses.dto.CourseUpdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CoursesService {
    private final HashMap<UUID, Course> courseStore = new HashMap<>();

    public CoursesService() {
        Course programmingMethodology = new Course(UUID.fromString("e0f8f134-4408-42e7-a9dd-34206c5f91f2"), "Programming Methodology", "Stanford", "software", "Mehran Sahami");
        Course linearAlgebra1 = new Course(UUID.fromString("ecf02406-a0ec-4cc2-a76a-f3598fb2c6f4"), "Linear Algebra", "AUTH", "Math", "Jabbour Nikolaos");
        Course electricalMachines = new Course(UUID.fromString("a41aa048-62b1-4196-a2c4-13207d3751c8"), "Electrical machines", "AUTH", "Engineering", "Jabbour Nikolaos");
        Course yogaScience = new Course(UUID.fromString("0a8c8472-c9b0-4a5a-a34b-893cabb7a40b"), "Yoga Science", "Buddha", "Tsakra", "Lila Nikolaou");
        courseStore.put(programmingMethodology.getId(), programmingMethodology);
        courseStore.put(linearAlgebra1.getId(), linearAlgebra1);
        courseStore.put(electricalMachines.getId(), electricalMachines);
        courseStore.put(yogaScience.getId(), yogaScience);
    }

    public HashMap<UUID, Course> getCourseStore() {
        return courseStore;
    }


    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>(courseStore.values());
        return courses;
    }

    //TODO check java Streams
    public List<Course> getFilteredList(List<Course> coursesList, String institution, String tag, String instructor) {
        List<Course> filteredList = new ArrayList<>();
        if (institution == null && tag == null && instructor == null) {
            filteredList.addAll(coursesList);
        } else {
            for (Course course : coursesList) {
//                boolean isMatch = true;
                if (instructor != null && !course.getInstructor().equals(instructor)) {
//                    isMatch = false;
                    continue;
                }
                if (institution != null && !course.getInstitutionName().equals(institution)) {
//                    isMatch = false;
                    continue;
                }
                if (tag != null && !course.getTag().equals(tag)) {
                    //                    isMatch = false;
                    continue;
                }
//                if (isMatch) {
                filteredList.add(course);
//                }
            }
        }
        return filteredList;
    }

    public Course getSpecificCourse(UUID id) {
        Course specificCourse = courseStore.get(id);

        return specificCourse;
    }


    public boolean deleteSpecificCourse(UUID id) {
        Course specificCourse = courseStore.remove(id);
        return specificCourse != null;
    }

    public Course replaceCourse(CourseCreate course, UUID id) {
        Course updatedCourse = new Course(id, course.getTitle(), course.getInstitutionName(), course.getTag(), course.getInstructor());
        courseStore.replace(updatedCourse.getId(), updatedCourse);
        return updatedCourse;
    }

    public Course createNewCourseInput(CourseCreate course) {
        Course createdCourse = new Course(course.getTitle(), course.getInstitutionName(), course.getTag(), course.getInstructor());
        createdCourse.generateId();
        courseStore.put(createdCourse.getId(), createdCourse);
        return createdCourse;
    }


    public Course partiallyUpdateCourse(CourseUpdate courseUpdate, UUID id) {
        Course courseToUpdate = courseStore.get(id);

        if (courseUpdate.getTitle() != null) {
            String newTitle = courseUpdate.getTitle();
            courseToUpdate.setTitle(newTitle);
        }

        if (courseUpdate.getInstitutionName() != null) {
            String institutionName = courseUpdate.getInstitutionName();
            courseToUpdate.setInstitutionName(institutionName);
        }

        if (courseUpdate.getInstructor() != null) {
            String newInstructorName = courseUpdate.getInstructor();
            courseToUpdate.setInstructor(newInstructorName);
        }

        if (courseUpdate.getTag() != null) {
            String newTag = courseUpdate.getTag();
            courseToUpdate.setTag(newTag);
        }

        return courseToUpdate;
    }


}