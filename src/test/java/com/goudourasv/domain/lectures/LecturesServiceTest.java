package com.goudourasv.domain.lectures;

import com.goudourasv.data.lectures.LecturesRepository;
import com.goudourasv.http.lectures.dto.LectureCreate;
import com.goudourasv.http.lectures.dto.LectureUpdate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static com.goudourasv.domain.utils.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LecturesServiceTest {
    @Mock
    private LecturesRepository lecturesRepository;
    @InjectMocks
    private LecturesService lecturesService;

    @Test
    public void shouldReturnFilteredLectures() {
        //given
        UUID courseId = UUID.fromString("2c3b2709-73ba-47f2-b4e2-3f0979ea0600");
        List<Lecture> lectures = createLectures();

        when(lecturesRepository.getFilteredLectures(courseId)).thenReturn(lectures);

        //when
        List<Lecture> expectedLectures = lecturesService.getFilteredLectures(courseId);

        //then
        verify(lecturesRepository).getFilteredLectures(courseId);
        assertThat(expectedLectures).hasSameSizeAs(lectures).hasSameElementsAs(lectures);
    }

//    //TODO Change test assertion to an exception and then change service's logic(Test Driven)
//    @Test
//    public void shouldThrowWhenCourseIdIsNull() {
//        //given
//        List<Lecture> lectures = createLectures();
//        UUID courseId = UUID.fromString("2c3b2709-73ba-47f2-b4e2-3f0979ea0600");
//
//        when(lecturesRepository.getFilteredLectures(null)).thenReturn(lectures);
//
//        //when
//        List<Lecture> expectedLectures = lecturesService.getFilteredLectures(null);
//
//        //then
//        verify(lecturesRepository).getFilteredLectures(null);
//        assertThat(expectedLectures).isEmpty();
//    }

    @Test
    public void shouldReturnSpecificLecture() {
        //given
        UUID lectureId = UUID.fromString("7f0c944c-d9b7-41af-8840-516240cb4584");
        Lecture lecture = createLecture();
        when(lecturesRepository.getSpecificLecture(lectureId)).thenReturn(lecture);

        //when
        Lecture expectedLecture = lecturesService.getSpecificLecture(lectureId);

        //then
        verify(lecturesRepository).getSpecificLecture(lectureId);
        assertThat(expectedLecture).isEqualTo(lecture);

    }

    @Test
    public void shouldCreateLecture() {
        //given
        LectureCreate lectureCreate = createLectureCreate();
        Lecture lecture = createLecture();

        when(lecturesRepository.createLecture(lectureCreate)).thenReturn(lecture);

        //when
        Lecture expectedLecture = lecturesService.createLecture(lectureCreate);

        //then
        verify(lecturesRepository).createLecture(lectureCreate);
        assertThat(expectedLecture).isEqualTo(lecture);
    }

    @Test
    public void shouldDeleteLecture() {
        UUID lectureId = UUID.fromString("7f0c944c-d9b7-41af-8840-516240cb4584");

        when(lecturesRepository.deleteSpecificLecture(lectureId)).thenReturn(true);

        //when
        boolean deleted = lecturesService.deleteSpecificLecture(lectureId);

        //then
        verify(lecturesRepository).deleteSpecificLecture(lectureId);
        assertTrue(deleted);
    }

    @Test
    public void shouldDeleteLectureWithNullId() {
        //given
        when(lecturesRepository.deleteSpecificLecture(null)).thenReturn(false);

        //when
        boolean deleted = lecturesService.deleteSpecificLecture(null);

        //then
        verify(lecturesRepository).deleteSpecificLecture(null);
        assertFalse(deleted);
    }

    @Test
    public void shouldReplaceLecture() {
        UUID lectureId = UUID.fromString("7f0c944c-d9b7-41af-8840-516240cb4584");
        LectureCreate lectureCreate = createLectureCreate();
        Lecture lecture = createLecture();

        when(lecturesRepository.replaceLecture(lectureId, lectureCreate)).thenReturn(lecture);

        //when
        Lecture expectedLecture = lecturesService.updateLecture(lectureId, lectureCreate);

        //then
        verify(lecturesRepository).replaceLecture(lectureId, lectureCreate);
        assertThat(expectedLecture).isEqualTo(lecture);

    }

    @Test
    public void shouldUpdateLecture() {
        //given
        UUID lectureId = UUID.fromString("7f0c944c-d9b7-41af-8840-516240cb4584");
        LectureUpdate lectureUpdate = createLectureUpdate();
        Lecture lecture = createLecture();

        when(lecturesRepository.partiallyUpdateLecture(lectureId, lectureUpdate)).thenReturn(lecture);

        //when
        Lecture expectedLecture = lecturesService.partiallyUpdateLecture(lectureId, lectureUpdate);

        //then

        verify(lecturesRepository).partiallyUpdateLecture(lectureId, lectureUpdate);
        assertThat(expectedLecture).isEqualTo(lecture);
    }

}
