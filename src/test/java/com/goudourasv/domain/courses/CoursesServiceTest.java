package com.goudourasv.domain.courses;

import com.goudourasv.data.courses.CoursesRepository;
import com.goudourasv.http.courses.dto.CourseCreate;
import com.goudourasv.http.courses.dto.CourseUpdate;
import com.goudourasv.http.users.dto.FavouriteCourseCreate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.goudourasv.utils.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CoursesServiceTest {
    @Mock
    private CoursesRepository coursesRepository;
    @InjectMocks
    private CoursesService coursesService;

    @Test
    public void shouldReturnAllCoursesFiltered() {
        //given
        List<Course> filteredCourses = createCourses();
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        UUID instructorId = UUID.fromString("7ce6be58-4eb1-4ff1-b470-a34c2fc54687");
        Set<String> tags = createTags();
        when(coursesRepository.getFilteredCourses(institutionId, tags, instructorId)).thenReturn(filteredCourses);

        //when
        List<Course> expectedCourses = coursesService.getFilteredCourses(institutionId, tags, instructorId);

        //then
        verify(coursesRepository).getFilteredCourses(institutionId, tags, instructorId);
        assertThat(expectedCourses).hasSize(filteredCourses.size()).hasSameElementsAs(filteredCourses);

    }

    @Test
    public void shouldReturnSpecificCourse() {
        // given
        UUID courseId = UUID.fromString("2c3b2709-73ba-47f2-b4e2-3f0979ea0600");
        Course course = createCourse();
        when(coursesRepository.getSpecificCourse(courseId)).thenReturn(course);
        // when
        Course specificCourse = coursesService.getSpecificCourse(courseId);

        // then
        verify(coursesRepository).getSpecificCourse(courseId);
        assertEquals(specificCourse.getId(), course.getId());
    }

    @Test
    public void shouldCreateCourse() {
        //given
        CourseCreate courseCreate = createCourseCreate();
        Course expectedCourse = createCourse();

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
        when(coursesRepository.deleteSpecificCourse(courseId)).thenReturn(true);

        //when
        boolean deleted = coursesService.deleteSpecificCourse(courseId);

        //then
        verify(coursesRepository).deleteSpecificCourse(courseId);
        assertTrue(deleted);

    }

    @Test
    public void shouldReplaceCourse() {
        //given
        UUID courseId = UUID.fromString("2c3b2709-73ba-47f2-b4e2-3f0979ea0600");
        CourseCreate courseCreate = createCourseCreate();
        Course expectedCourse = createCourse();

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
        UUID courseId = UUID.fromString("2c3b2709-73ba-47f2-b4e2-3f0979ea0600");
        CourseUpdate courseUpdate = createCourseUpdate();
        Course expectedCourse = createCourse();
        when(coursesRepository.partiallyUpdateCourse(courseUpdate, courseId)).thenReturn(expectedCourse);

        //when
        Course updatedCourse = coursesService.partiallyUpdateCourse(courseUpdate, courseId);

        //then
        verify(coursesRepository).partiallyUpdateCourse(courseUpdate, courseId);
        assertEquals(updatedCourse, expectedCourse);
    }

    @Test
    public void shouldCreateFavourite() {
        //given
        FavouriteCourseCreate favouriteCourseCreate = createFavouriteCourseCreate();
        Course expectedCourse = createCourse();

        when(coursesRepository.createFavouriteCourse(favouriteCourseCreate)).thenReturn(expectedCourse);

        //when
        Course createdFavouriteCourse = coursesService.createFavourite(favouriteCourseCreate);

        //then
        verify(coursesRepository).createFavouriteCourse(favouriteCourseCreate);
        assertEquals(createdFavouriteCourse, expectedCourse);
    }

    @Test
    public void shouldDeleteSpecificFavourite() {
        //given
        UUID courseId = UUID.fromString("165f03a3-a4a3-48ca-8c8d-78ea591194cb");
        UUID userId = UUID.fromString("38c5f6a0-8319-4a43-bd8d-05c762513179 ");
        when(coursesRepository.deleteSpecificFavourite(userId, courseId)).thenReturn(true);

        //when
        boolean deleted = coursesService.deleteSpecificCourse(courseId);

        //then
        verify(coursesRepository).deleteSpecificFavourite(userId, courseId);
        assertTrue(deleted);

    }


    //TODO:check this test doesn't works

//    @Test
//    public void shouldReturnFavouriteCoursesOfUser() {
//        //given
//        UUID userId = UUID.fromString("38c5f6a0-8319-4a43-bd8d-05c762513179 ");
//        List<Course> favouriteCourses = createCourses();
//
//        when(coursesRepository.getFavouriteCourses(userId)).thenReturn(favouriteCourses);
//        //when
//        List<Course> expectedFavouriteCourses = coursesService.getFavouriteCourses(userId);
//
//        //then
//        verify(coursesRepository).getFavouriteCourses(userId);
//        assertThat(expectedFavouriteCourses).hasSize(favouriteCourses.size()).hasSameElementsAs(favouriteCourses);
//    }
}
