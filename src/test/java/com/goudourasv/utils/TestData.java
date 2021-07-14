package com.goudourasv.utils;

import com.goudourasv.domain.courses.Course;
import com.goudourasv.domain.courses.CourseLecture;
import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.domain.tags.Tag;
import com.goudourasv.http.courses.dto.CourseCreate;
import com.goudourasv.http.courses.dto.CourseUpdate;
import com.goudourasv.http.institutions.dto.InstitutionCreate;
import com.goudourasv.http.institutions.dto.InstitutionUpdate;
import com.goudourasv.http.instructors.dto.InstructorCreate;
import com.goudourasv.http.instructors.dto.InstructorUpdate;
import com.goudourasv.http.lectures.dto.LectureCreate;
import com.goudourasv.http.lectures.dto.LectureUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class TestData {


    public static List<Course> createCourses() {
        List<Course> courses = new ArrayList<>();

        List<Institution> institutions1 = new ArrayList<>();
        List<Institution> institutions2 = new ArrayList<>();
        List<Institution> institutions3 = new ArrayList<>();
        List<Institution> institutions4 = new ArrayList<>();
        Institution institution1 = new Institution(UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83"), "AUTH");
        Institution institution2 = new Institution(UUID.fromString("86664d56-71c6-4de6-9771-cb8e707c8674"), "Tandra");
        Institution institution3 = new Institution(UUID.fromString("daad559f-4755-4d8a-9d3c-5e039e2ceb2f"), "UOM");
        Institution institution4 = new Institution(UUID.fromString("0ba2fe42-b199-4584-baf8-0059199eaaa1"), "LSE");
        institutions1.add(institution1);
        institutions2.add(institution2);
        institutions3.add(institution3);
        institutions4.add(institution4);


        List<Instructor> instructors = new ArrayList<>();
        Instructor instructor1 = new Instructor(UUID.fromString("278553ff-c001-4ac3-a5ea-71141e855704"), "Mehran", "Sahami", institutions2);
        Instructor instructor2 = new Instructor(UUID.fromString("7ce6be58-4eb1-4ff1-b470-a34c2fc54687"), "Nikolaos", "Jabbour", institutions1);
        Instructor instructor3 = new Instructor(UUID.fromString("c65abe61-fa7b-4fb8-aa17-f8103c12957a"), "Rodoula", "Tsiotsiou", institutions3);
        Instructor instructor4 = new Instructor(UUID.fromString("7c30c894-cd0c-4c57-9d11-e6fd2913514b"), "Alex", "Taylor", institutions4);
        instructors.add(instructor1);
        instructors.add(instructor2);
        instructors.add(instructor3);
        instructors.add(instructor4);

        List<Tag> tags1 = new ArrayList<>();
        List<Tag> tags2 = new ArrayList<>();
        List<Tag> tags3 = new ArrayList<>();
        List<Tag> tags4 = new ArrayList<>();
        Tag software = new Tag("Software");
        Tag engineering = new Tag("Engineering");
        Tag economics = new Tag("Economics");
        Tag mathematics = new Tag("Mathematics");
        Tag statistics = new Tag("Statistics");
        tags1.add(engineering);
        tags2.add(economics);
        tags3.add(economics);
        tags3.add(mathematics);
        tags3.add(statistics);
        tags4.add(software);


        Course course1 = new Course(UUID.fromString("2c3b2709-73ba-47f2-b4e2-3f0979ea0600"), "Electrical engines", institution1, tags1, instructor2, null, null);
        Course course2 = new Course(UUID.fromString("d946bc18-e92a-407b-980c-301c2bf3b44b"), "Marketing", institution3, tags2, instructor3, null, null);
        Course course3 = new Course(UUID.fromString("165f03a3-a4a3-48ca-8c8d-78ea591194cb"), "Statistics", institution4, tags3, instructor4, null, null);
        Course course4 = new Course(UUID.fromString("24c74444-fadb-4e91-8604-f299ad6189ed"), "Programming Methodology", institution1, tags4, instructor2, null, null);
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);


        return courses;
    }

    public static List<Instructor> createInstructors() {
        List<Institution> institutions1 = new ArrayList<>();
        List<Institution> institutions2 = new ArrayList<>();
        List<Institution> institutions3 = new ArrayList<>();
        List<Institution> institutions4 = new ArrayList<>();
        Institution institution1 = new Institution(UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83"), "AUTH");
        Institution institution2 = new Institution(UUID.fromString("86664d56-71c6-4de6-9771-cb8e707c8674"), "Tandra");
        Institution institution3 = new Institution(UUID.fromString("daad559f-4755-4d8a-9d3c-5e039e2ceb2f"), "UOM");
        Institution institution4 = new Institution(UUID.fromString("0ba2fe42-b199-4584-baf8-0059199eaaa1"), "LSE");
        institutions1.add(institution1);
        institutions2.add(institution2);
        institutions3.add(institution3);
        institutions4.add(institution4);


        List<Instructor> instructors = new ArrayList<>();
        Instructor instructor1 = new Instructor(UUID.fromString("278553ff-c001-4ac3-a5ea-71141e855704"), "Mehran", "Sahami", institutions2);
        Instructor instructor2 = new Instructor(UUID.fromString("7ce6be58-4eb1-4ff1-b470-a34c2fc54687"), "Nikolaos", "Jabbour", institutions1);
        Instructor instructor3 = new Instructor(UUID.fromString("c65abe61-fa7b-4fb8-aa17-f8103c12957a"), "Rodoula", "Tsiotsiou", institutions3);
        Instructor instructor4 = new Instructor(UUID.fromString("7c30c894-cd0c-4c57-9d11-e6fd2913514b"), "Alex", "Taylor", institutions4);
        instructors.add(instructor1);
        instructors.add(instructor2);
        instructors.add(instructor3);
        instructors.add(instructor4);
        return instructors;

    }

    public static List<Institution> createInstitutions() {
        List<Institution> institutions = new ArrayList<>();
        Institution institution1 = new Institution(UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83"), "AUTH");
        Institution institution2 = new Institution(UUID.fromString("86664d56-71c6-4de6-9771-cb8e707c8674"), "Tandra");
        Institution institution3 = new Institution(UUID.fromString("daad559f-4755-4d8a-9d3c-5e039e2ceb2f"), "UOM");
        Institution institution4 = new Institution(UUID.fromString("0ba2fe42-b199-4584-baf8-0059199eaaa1"), "LSE");
        institutions.add(institution1);
        institutions.add(institution2);
        institutions.add(institution3);
        institutions.add(institution4);
        return institutions;

    }

    public static List<Lecture> createLectures() {
        List<Lecture> lectures = new ArrayList<>();
        CourseLecture courseLecture = new CourseLecture(UUID.fromString("2c3b2709-73ba-47f2-b4e2-3f0979ea0600"), "Electrical Engines");
        Lecture lecture1 = new Lecture(UUID.fromString("222c6686-d6dd-4b29-83c7-36abca11f146"), "Lecture 1", courseLecture, null, null);
        Lecture lecture2 = new Lecture(UUID.fromString("caeb27ae-e1c1-4104-aad7-ada1e44210ad"), "Lecture 2", courseLecture, null, null);
        lectures.add(lecture1);
        lectures.add(lecture2);
        return lectures;
    }

    public static  List<String> createTags(){
        Tag softwareTag = new Tag("Software");
        Tag engineeringTag = new Tag("Engineering");
        String software = softwareTag.getName();
        String engineering = engineeringTag.getName();
        List<String> tags = new ArrayList<>();
        tags.add(software);
        tags.add(engineering);
        return tags;

        }

    public static Course createCourse() {
        List<Institution> institutions1 = new ArrayList<>();
        Institution institution1 = new Institution(UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83"), "AUTH");
        institutions1.add(institution1);

        Instructor instructor2 = new Instructor(UUID.fromString("7ce6be58-4eb1-4ff1-b470-a34c2fc54687"), "Nikolaos", "Jabbour", institutions1);

        Course course1 = new Course(UUID.fromString("2c3b2709-73ba-47f2-b4e2-3f0979ea0600"), "Electrical engines", institution1, null, instructor2, null, null);
        return course1;
    }

    public static CourseCreate createCourseCreate() {
        List<Institution> institutions1 = new ArrayList<>();
        Institution institution1 = new Institution(UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83"), "AUTH");
        institutions1.add(institution1);

        UUID instructorId2 = UUID.fromString("7ce6be58-4eb1-4ff1-b470-a34c2fc54687");

        CourseCreate courseCreate1 = new CourseCreate("Electrical engines", UUID.fromString("2c3b2709-73ba-47f2-b4e2-3f0979ea0600"), null, instructorId2, null, null);
        return courseCreate1;
    }

    public static CourseUpdate createCourseUpdate() {
        List<Institution> institutions1 = new ArrayList<>();
        Institution institution1 = new Institution(UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83"), "AUTH");
        institutions1.add(institution1);

        UUID instructorId2 = UUID.fromString("7ce6be58-4eb1-4ff1-b470-a34c2fc54687");

        CourseUpdate courseUpdate1 = new CourseUpdate("Electrical engines", UUID.fromString("2c3b2709-73ba-47f2-b4e2-3f0979ea0600"), null, instructorId2, null, null);
        return courseUpdate1;
    }


    public static Instructor createInstructor() {
        List<Institution> institutions1 = new ArrayList<>();
        Institution institution1 = new Institution(UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83"), "AUTH");
        institutions1.add(institution1);
        Instructor instructor2 = new Instructor(UUID.fromString("7ce6be58-4eb1-4ff1-b470-a34c2fc54687"), "Nikolaos", "Jabbour", institutions1);

        return instructor2;
    }

    public static InstructorCreate createInstructorCreate() {
        List<UUID> institutionIds = new ArrayList<>();
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        institutionIds.add(institutionId);
        InstructorCreate instructorCreate = new InstructorCreate("Nikolaos", "Jabbour", institutionIds);

        return instructorCreate;
    }

    public static InstructorUpdate createInstructorUpdate() {
        List<UUID> institutionIds = new ArrayList<>();
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        institutionIds.add(institutionId);
        InstructorUpdate instructorUpdate = new InstructorUpdate("Nikolaos", "Jabbour", institutionIds);

        return instructorUpdate;
    }

    public static Institution createInstitution() {
        Institution institution1 = new Institution(UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83"), "AUTH");
        return institution1;

    }

    public static InstitutionCreate createInstitutionCreate() {
        List<UUID> instructorsId = new ArrayList<>();
        UUID instructorId = UUID.fromString("7ce6be58-4eb1-4ff1-b470-a34c2fc54687");
        instructorsId.add(instructorId);
        InstitutionCreate institutionCreate1 = new InstitutionCreate("AUTH", "Greece", "Thessaloniki", instructorsId);
        return institutionCreate1;

    }

    public static InstitutionUpdate createInstitutionUpdate() {
        List<UUID> instructorsId = new ArrayList<>();
        UUID instructorId = UUID.fromString("7ce6be58-4eb1-4ff1-b470-a34c2fc54687");
        instructorsId.add(instructorId);
        InstitutionUpdate institutionUpdate1 = new InstitutionUpdate("AUTH", "Greece", "Thessaloniki");
        return institutionUpdate1;

    }

    public static Lecture createLecture() {
        CourseLecture courseLecture = new CourseLecture(UUID.fromString("2c3b2709-73ba-47f2-b4e2-3f0979ea0600"), "Electrical Engines");
        Lecture lecture = new Lecture(UUID.fromString("7f0c944c-d9b7-41af-8840-516240cb4584"), "Lecture 3", courseLecture, null, null);
        return lecture;

    }

    public static LectureCreate createLectureCreate() {
        UUID courseId = UUID.fromString("2c3b2709-73ba-47f2-b4e2-3f0979ea0600");
        LectureCreate lectureCreate = new LectureCreate("Lecture 3", null, null, courseId);
        return lectureCreate;
    }

    public static LectureUpdate createLectureUpdate() {
        UUID courseId = UUID.fromString("2c3b2709-73ba-47f2-b4e2-3f0979ea0600");
        LectureUpdate lectureUpdate = new LectureUpdate("Lecture 4", null, null, courseId);
        return lectureUpdate;

    }
}
