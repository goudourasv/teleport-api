package com.goudourasv.domain.courses;

import com.goudourasv.data.courses.CoursesRepository;
import com.goudourasv.domain.institutions.InstitutionsService;
import com.goudourasv.domain.instructors.InstructorsService;
import com.goudourasv.domain.tags.TagsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void shouldReturnSpecificCourse() {
        // given
        UUID courseId = UUID.fromString("165f03a3-a4a3-48ca-8c8d-78ea591194cb");
        Course course = new Course(courseId,"Yoga",null,null,null,null,null);
        when(coursesRepository.getSpecificCourse(courseId)).thenReturn(course);
        // when
        Course specificCourse = coursesService.getSpecificCourse(courseId);
        // then
        verify(coursesRepository).getSpecificCourse(courseId);
        assertEquals(specificCourse.getId(),course.getId());




    }



}
