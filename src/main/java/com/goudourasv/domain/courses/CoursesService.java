package com.goudourasv.domain.courses;

import com.goudourasv.data.courses.CoursesRepository;
import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.institutions.InstitutionsService;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.instructors.InstructorsService;
import com.goudourasv.domain.tags.Tag;
import com.goudourasv.domain.tags.TagsService;
import com.goudourasv.http.courses.dto.CourseCreate;
import com.goudourasv.http.courses.dto.CourseUpdate;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CoursesService {
    private final HashMap<UUID, Course> courseStore = new HashMap<>();
    private InstitutionsService institutionsService = new InstitutionsService();
    private InstructorsService instructorsService = new InstructorsService();
    private TagsService tagsService;
    private CoursesRepository coursesRepository;

    public CoursesService(TagsService tagsService, CoursesRepository coursesRepository) {
        Tag tsakra = new Tag("tsakra");
        Tag engineering = new Tag("engineering");
        Tag software = new Tag("software");
        Tag math = new Tag("math");

        Instructor mehranSahami = new Instructor(UUID.fromString("86664d56-71c6-4de6-9771-cb8e707c8674"), "Mehran", "Sahami", "Stanford");
        Instructor jabbourNikolaos = new Instructor(UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83"), "Nikolaos", "Jabbour", "Auth");
        Instructor lilaNikolaou = new Instructor(UUID.fromString("daad559f-4755-4d8a-9d3c-5e039e2ceb2f"), "Lila", "Nikolaou", "Buddha");

        Institution stanford = new Institution(UUID.fromString("cdf2b504-ad7c-46e4-bd01-7e7040ed3052"), "stanford", "USA", "California");
        Institution auth = new Institution(UUID.fromString("d5c83909-c699-40b5-ac04-c65b558a16c3"), "auth", "Greece", "Thessaloniki");
        Institution buddha = new Institution(UUID.fromString("cc249d1c-c001-4140-ab0d-1b25e7d64f42"), "buddha", "Nepal", "Kathmandu");

        Course programmingMethodology = new Course(UUID.fromString("e0f8f134-4408-42e7-a9dd-34206c5f91f2"), "Programming Methodology", stanford, software, mehranSahami,null,null);
        Course linearAlgebra1 = new Course(UUID.fromString("ecf02406-a0ec-4cc2-a76a-f3598fb2c6f4"), "Linear Algebra", auth, math, jabbourNikolaos,null,null);
        Course electricalMachines = new Course(UUID.fromString("a41aa048-62b1-4196-a2c4-13207d3751c8"), "Electrical machines", auth, engineering, jabbourNikolaos,null,null);
        Course yogaScience = new Course(UUID.fromString("0a8c8472-c9b0-4a5a-a34b-893cabb7a40b"), "Yoga Science", buddha, tsakra, lilaNikolaou,null,null);

        courseStore.put(programmingMethodology.getId(), programmingMethodology);
        courseStore.put(linearAlgebra1.getId(), linearAlgebra1);
        courseStore.put(electricalMachines.getId(), electricalMachines);
        courseStore.put(yogaScience.getId(), yogaScience);
        this.tagsService = tagsService;
        this.coursesRepository = coursesRepository;
    }

    public HashMap<UUID, Course> getCourseStore() {
        return courseStore;
    }


    public List<Course> getCourses() {
        List<Course> courses = coursesRepository.getCourses();
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
                if (institution != null && !course.getInstitution().equals(institution)) {
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
        //TODO Handle startDate and endDate
        Course updatedCourse = new Course(id, course.getTitle(), course.getInstitution(), course.getTag(), course.getInstructor(),null,null);
        courseStore.replace(updatedCourse.getId(), updatedCourse);
        return updatedCourse;
    }

    public Course createNewCourseInput(CourseCreate course) {
        Course createdCourse = new Course(course.getTitle(), course.getInstitution(), course.getTag(), course.getInstructor());
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

        if (courseUpdate.getInstitutionId() != null) {
            UUID institutionId = courseUpdate.getInstitutionId();
            Institution institution = institutionsService.getSpecificInstitution(institutionId);
            courseToUpdate.setInstitution(institution);
        }

        if (courseUpdate.getInstructorId() != null) {
            UUID instructorId = courseUpdate.getInstructorId();
            Instructor instructor = instructorsService.getSpecificInstructor(instructorId);
            courseToUpdate.setInstructor(instructor);
        }

        if (courseUpdate.getTag() != null) {
            String tag = courseUpdate.getTag();
            Tag newTag = tagsService.getSpecificTag(tag);
            courseToUpdate.setTag(newTag);
        }

        return courseToUpdate;
    }

    //TODO live course list
    public List<LiveCourse> getLiveCourses() {
        List<LiveCourse> liveCourses = new ArrayList<>();
        return liveCourses;


    }
}