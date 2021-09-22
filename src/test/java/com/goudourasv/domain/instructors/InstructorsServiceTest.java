package com.goudourasv.domain.instructors;

import com.goudourasv.data.instructors.InstructorsRepository;
import com.goudourasv.http.instructors.dto.InstructorCreate;
import com.goudourasv.http.instructors.dto.InstructorUpdate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static com.goudourasv.utils.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InstructorsServiceTest {
    @Mock
    private InstructorsRepository instructorsRepository;
    @InjectMocks
    private InstructorsService instructorsService;


    @Test
    public void shouldReturnInstructors() {
        //given
        List<Instructor> instructors = createInstructors();

        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        when(instructorsRepository.getInstructors(institutionId)).thenReturn(instructors);

        // when
        List<Instructor> expectedInstructors = instructorsService.getInstructors(institutionId);

        //then
        verify(instructorsRepository).getInstructors(institutionId);
        assertThat(expectedInstructors).hasSameSizeAs(instructors).hasSameElementsAs(instructors);
    }

    @Test
    public void shouldReturnInstructorsWithNullInstitutionId() {
        //given
        List<Instructor> instructors = createInstructors();

        when(instructorsRepository.getInstructors(null)).thenReturn(instructors);

        //when
        List<Instructor> expectedInstructors = instructorsService.getInstructors(null);

        //then
        verify(instructorsRepository).getInstructors(null);
        assertThat(expectedInstructors).hasSameSizeAs(instructors).hasSameElementsAs(instructors);
    }

    @Test
    public void shouldReturnInstructorsWithInstitutionIdThatNotExists() {
        //given
        List<Instructor> instructors = createInstructors();
        UUID institutionId = UUID.fromString("ee9e2bcb-7d26-4673-b6d6-f1dae9a6afeb");

        when(instructorsRepository.getInstructors(institutionId)).thenReturn(instructors);

        //when
        List<Instructor> expectedInstructors = instructorsService.getInstructors(institutionId);

        //then
        verify(instructorsRepository).getInstructors(institutionId);
        assertThat(expectedInstructors).hasSameSizeAs(instructors).hasSameElementsAs(instructors);
    }

    @Test
    public void shouldReturnSpecificInstructor() {
        //given
        UUID instructorId = UUID.fromString("7ce6be58-4eb1-4ff1-b470-a34c2fc54687");
        Instructor instructor = createInstructor();

        when(instructorsRepository.getSpecificInstructor(instructorId)).thenReturn(instructor);

        //when
        Instructor expectedInstructor = instructorsService.getSpecificInstructor(instructorId);

        //then
        verify(instructorsRepository).getSpecificInstructor(instructorId);
        assertThat(expectedInstructor).isEqualTo(instructor);


    }

    @Test
    public void shouldCreateInstructor() {
        //given
        InstructorCreate instructorCreate = createInstructorCreate();
        Instructor instructor = createInstructor();

        when(instructorsRepository.createNewInstructor(instructorCreate)).thenReturn(instructor);

        //when
        Instructor expectedInstructor = instructorsService.createNewInstructor(instructorCreate);

        //then
        verify(instructorsRepository).createNewInstructor(instructorCreate);
        assertThat(expectedInstructor).isEqualTo(instructor);

    }

    @Test
    public void shouldDeleteSpecificInstructor() {
        //given
        UUID instructorId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        when(instructorsRepository.deleteSpecificInstructor(instructorId)).thenReturn(true);

        //when
        boolean deleted = instructorsService.deleteSpecificInstructor(instructorId);

        //then
        verify(instructorsRepository).deleteSpecificInstructor(instructorId);
        assertTrue(deleted);
    }

    @Test
    public void shouldReplaceInstructor() {
        //given
        UUID instructorId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        InstructorCreate instructorCreate = createInstructorCreate();
        Instructor instructor = createInstructor();

        when(instructorsRepository.replaceInstructor(instructorId, instructorCreate)).thenReturn(instructor);

        //when
        Instructor expectedInstructor = instructorsService.replaceInstructor(instructorId, instructorCreate);

        //then
        verify(instructorsRepository).replaceInstructor(instructorId, instructorCreate);
        assertThat(expectedInstructor).isEqualTo(instructor);

    }

    @Test
    public void shouldUpdateInstructor() {
        //given
        UUID instructorId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        InstructorUpdate instructorUpdate = createInstructorUpdate();
        Instructor instructor = createInstructor();

        when(instructorsRepository.partiallyUpdateInstructor(instructorUpdate, instructorId)).thenReturn(instructor);

        //when
        Instructor expectedInstructor = instructorsService.partiallyUpdateInstructor(instructorId, instructorUpdate);

        //then
        verify(instructorsRepository).partiallyUpdateInstructor(instructorUpdate, instructorId);
        assertThat(expectedInstructor).isEqualTo(instructor);
    }


}
