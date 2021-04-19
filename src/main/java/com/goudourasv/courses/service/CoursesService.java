package com.goudourasv.courses.service;

import com.goudourasv.courses.controller.dto.CourseUpdate;
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

    public List<Course> getFilteredList(List<Course> coursesList, String institution, String tag, String professor) {
        List<Course> filteredList = new ArrayList<>();
        if (institution == null && tag == null && professor == null) {
            filteredList.addAll(coursesList);
        } else {
            for (Course course : coursesList) {

                if (course.getInstitutionName().equals(institution) && course.getTag().equals(tag) && course.getProfessor().equals(professor)) {
                    filteredList.add(course);

                } else if (course.getInstitutionName().equals(institution) && course.getTag().equals(tag) && professor == null) {
                    filteredList.add(course);

                } else if (course.getInstitutionName().equals(institution) && course.getProfessor().equals(professor) && tag == null) {
                    filteredList.add(course);

                } else if (course.getTag().equals(tag) && course.getProfessor().equals(professor) && institution == null) {
                    filteredList.add(course);

                } else if (course.getProfessor().equals(professor) && institution == null && tag == null) {
                    filteredList.add(course);

                } else if (course.getTag().equals(tag) && professor == null && institution == null) {
                    filteredList.add(course);

                } else if (course.getInstitutionName().equals(institution) && professor == null && tag == null) {
                    filteredList.add(course);
                }
            }
        }
        return filteredList;
    }

    public Course getSpecificCourse(int id) {
        Course specificCourse = courseStore.get(id);

        return specificCourse;
    }

    public Course createNewCourse(Course course) {
        courseStore.put(course.getId(), course);
        return course;
    }

    public boolean deleteSpecificCourse(int id) {
        Course specificCourse = courseStore.remove(id);
        return specificCourse != null;
    }

    public Course replaceCourse(Course course) {
        Course updatedCourse = courseStore.replace(course.getId(), course);
        return updatedCourse;
    }


    public Course partiallyUpdateCourse(CourseUpdate courseUpdate, int id) {
        Course courseToUpdate = courseStore.get(id);

        if (courseUpdate.getTitle() != null) {
            String newTitle = courseUpdate.getTitle();
            courseToUpdate.setTitle(newTitle);
        }

        if (courseUpdate.getInstitutionName() != null) {
            String institutionName = courseUpdate.getInstitutionName();
            courseToUpdate.setInstitutionName(institutionName);
        }

        if (courseUpdate.getProfessor() != null) {
            String newProfessorName = courseUpdate.getProfessor();
            courseToUpdate.setProfessor(newProfessorName);
        }

        if (courseUpdate.getTag() != null) {
            String newTag = courseUpdate.getTag();
            courseToUpdate.setTag(newTag);
        }

        return courseToUpdate;
    }


}