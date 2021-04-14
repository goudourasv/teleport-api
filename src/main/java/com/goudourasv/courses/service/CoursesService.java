package com.goudourasv.courses.service;

import com.goudourasv.courses.domain.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoursesService {
    private final HashMap<Integer, Course> courseStore = new HashMap<>();

    public CoursesService() {
        Course programmingMethodology = new Course(1, "Programming Methodology", "Stanford", "software", "Mehran Sahami");
        Course linearAlgebra1 = new Course(2, "Linear Algebra", "AUTH", "Math", "Jabbour Nikolaos");
        Course electricalMachines = new Course(3, "Electrical machines", "AUTH", "Engineering", "Jabbour Nikolaos");
        Course yogaScience = new Course(4, "Yoga Science", "Buddha", "Tsakra", "Lila Nikolaou");
        courseStore.put(1, programmingMethodology);
        courseStore.put(2, linearAlgebra1);
        courseStore.put(3, electricalMachines);
        courseStore.put(4, yogaScience);
    }

    public HashMap<Integer, Course> getCourseStore() {
        return courseStore;
    }

    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>(courseStore.values());
        return courses;
    }

    public List<Course> getFilteredList(List<Course> coursesList, String institution) {
        List<Course> filteredByInstitutionList = new ArrayList<>();
        for (Course course : coursesList) {
            if (course.getInstitutionName().equals(institution)) {
                filteredByInstitutionList.add(course);
            }
        }
        return filteredByInstitutionList;
    }

    public Course createNewCourse(Course course){
        courseStore.put(course.getId(), course);
        return course;
    }
}