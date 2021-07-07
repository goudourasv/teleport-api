package com.goudourasv.domain.courses;

import com.goudourasv.data.courses.CoursesRepository;
import com.goudourasv.domain.institutions.InstitutionsService;
import com.goudourasv.domain.instructors.InstructorsService;
import com.goudourasv.domain.tags.TagsService;
import com.goudourasv.http.courses.dto.CourseCreate;
import com.goudourasv.http.courses.dto.CourseUpdate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.TextParsingException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CoursesServiceTest {
    @Mock
    private InstitutionsService institutionsService;
    @Mock
    private InstructorsService instructorsService;
    @Mock
    private TagsService tagsService;
    @Mock
    private CoursesRepository coursesRepository;
    @InjectMocks
    private CoursesService coursesService;

    @Test
    public void shouldReturnAllCoursesFiltered() {
        //given
        Course course = new Course(UUID.fromString("165f03a3-a4a3-48ca-8c8d-78ea591194cb"), "Yoga", null, null, null, null, null);
        List<Course> filteredCourses = new ArrayList<>();
        filteredCourses.add(course);
        UUID institutionId = UUID.randomUUID();
        UUID instructorId = UUID.randomUUID();
        String tag = "tag";
        when(coursesRepository.getFilteredCourses(institutionId, tag, instructorId)).thenReturn(filteredCourses);


        //when
        List<Course> expectedCourses = coursesService.getFilteredCourses(institutionId, tag, instructorId);


        //then
        verify(coursesRepository).getFilteredCourses(institutionId, tag, instructorId);
        assertThat(expectedCourses).hasSameElementsAs(filteredCourses);
    }

    @Test
    public void shouldReturnSpecificCourse() {
        // given
        UUID courseId = UUID.fromString("165f03a3-a4a3-48ca-8c8d-78ea591194cb");
        Course course = new Course(courseId, "Yoga", null, null, null, null, null);

        // when
        Course specificCourse = coursesService.getSpecificCourse(courseId);

        // then
        verify(coursesRepository).getSpecificCourse(courseId);
        assertEquals(specificCourse.getId(), course.getId());
    }

    @Test
    public void shouldCreateCourse() {
        //given
        CourseCreate courseCreate = new CourseCreate("yoga", null, null, null, null, null);
        Course expectedCourse = new Course(UUID.randomUUID(), "yoga", null, null, null, null, null);
        when(coursesRepository.createCourse(courseCreate)).thenReturn(expectedCourse);
        //when
        Course createdCourse = coursesService.createCourse(courseCreate);

        //then
        verify(coursesRepository).createCourse(courseCreate);
        assertEquals(createdCourse.getTitle(), expectedCourse.getTitle());


    }

    @Test
    public void shouldDeleteCourse() {
        //given
        UUID courseId = UUID.fromString("165f03a3-a4a3-48ca-8c8d-78ea591194cb");
        Course course = new Course(courseId, "Yoga", null, null, null, null, null);
        when(coursesRepository.deleteSpecificCourse(courseId)).thenReturn(true);

        //when
        boolean deleted = coursesService.deleteSpecificCourse(courseId);

        //then
        verify(coursesRepository).deleteSpecificCourse(courseId);
        assertTrue(deleted);
        assertEquals(course.getId(), courseId);


    }

    @Test
    public void shouldReplaceCourse() {
        //given
        UUID courseId = UUID.fromString("165f03a3-a4a3-48ca-8c8d-78ea591194cb");
        CourseCreate courseCreate = new CourseCreate("yoga", null, null, null, null, null);
        Course expectedCourse = new Course(courseId, "yoga", null, null, null, null, null);
        when(coursesRepository.replaceCourse(courseCreate, courseId)).thenReturn(expectedCourse);

        //when
        Course replacedCourse = coursesService.replaceCourse(courseCreate, courseId);

        //then
        verify(coursesRepository).replaceCourse(courseCreate, courseId);
        assertEquals(expectedCourse, replacedCourse);

    }

    @Test
    public void shouldPartiallyUpdateCourse() {
        //given
        UUID courseId = UUID.fromString("165f03a3-a4a3-48ca-8c8d-78ea591194cb");
        CourseUpdate courseUpdate = new CourseUpdate("yoga",null,null,null,null,null);
        Course expectedCourse = new Course(courseId,"yoga",null,null,null,null,null);
        when(coursesRepository.partiallyUpdateCourse(courseUpdate,courseId)).thenReturn(expectedCourse);

        //when
        Course updatedCourse = coursesService.partiallyUpdateCourse(courseUpdate,courseId);

        //then
        verify(coursesRepository).partiallyUpdateCourse(courseUpdate,courseId);
        assertEquals(updatedCourse,expectedCourse);


    }

}
