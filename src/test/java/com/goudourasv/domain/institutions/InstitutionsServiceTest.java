package com.goudourasv.domain.institutions;

import com.goudourasv.data.institutions.InstitutionsRepository;
import com.goudourasv.domain.courses.CoursesService;
import com.goudourasv.domain.instructors.InstructorsService;
import com.goudourasv.domain.tags.TagsService;
import com.goudourasv.http.institutions.dto.InstitutionCreate;
import com.goudourasv.http.institutions.dto.InstitutionUpdate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class InstitutionsServiceTest {
    @Mock
    private InstructorsService instructorsService;
    @Mock
    private TagsService tagsService;
    @Mock
    private CoursesService coursesService;
    @Mock
    private InstitutionsRepository institutionsRepository;
    @InjectMocks
    private InstitutionsService institutionsService;


    @Test
    public void shouldReturnAllInstitutions() {
        //given
        List<Institution> institutions = new ArrayList<>();
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        String institutionName = "AUTH";
        Institution institution = new Institution(institutionId, institutionName);
        institutions.add(institution);
        when(institutionsRepository.getInstitutions()).thenReturn(institutions);

        //when
        List<Institution> expectedInstitutions = institutionsService.getInstitutions();

        //then
        verify(institutionsRepository).getInstitutions();
        assertThat(expectedInstitutions).hasSameSizeAs(institutions).hasSameElementsAs(institutions);
    }


    @Test
    public void shouldReturnSpecificInstitution() {
        //given
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        String institutionName = "AUTH";
        Institution institution = new Institution(institutionId, institutionName);
        when(institutionsRepository.getSpecificInstitution(institutionId)).thenReturn(institution);

        //when
        Institution expectedInstitution = institutionsService.getSpecificInstitution(institutionId);

        //then
        verify(institutionsRepository).getSpecificInstitution(institutionId);
        assertEquals(expectedInstitution, institution);
    }

    @Test
    public void shouldCreateInstitution() {
        //given
        String institutionName = "AUTH";
        InstitutionCreate institutionCreate = new InstitutionCreate(institutionName, null, null, null);
        Institution institution = new Institution(institutionName, null, null);
        when(institutionsRepository.createInstitution(institutionCreate)).thenReturn(institution);

        //when
        Institution expectedInstitution = institutionsService.createInstitution(institutionCreate);

        //then
        verify(institutionsRepository).createInstitution(institutionCreate);
        assertEquals(expectedInstitution, institution);


    }

    @Test
    public void shouldDeleteSpecificInstitution() {
        //given
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        when(institutionsRepository.deleteSpecificInstitution(institutionId)).thenReturn(true);

        //when
        boolean deleted = institutionsService.deleteSpecificCourse(institutionId);

        //then
        verify(institutionsRepository).deleteSpecificInstitution(institutionId);
        assertTrue(deleted);

    }

    @Test
    public void shouldReplaceInstitution() {
        //given
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        String institutionName = "AUTH";
        InstitutionCreate institutionCreate = new InstitutionCreate(institutionName, null, null, null);
        Institution institution = new Institution(institutionName, null, null);
        when(institutionsRepository.replaceInstitution(institutionCreate, institutionId)).thenReturn(institution);

        //when
        Institution expectedInstitution = institutionsService.replaceInstitution(institutionCreate, institutionId);

        //then
        verify(institutionsRepository).replaceInstitution(institutionCreate, institutionId);
        assertEquals(expectedInstitution, institution);


    }

    @Test
    public void shouldPartiallyUpdateInstitution() {
        //given
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        String institutionName = "AUTH";
        InstitutionUpdate institutionUpdate = new InstitutionUpdate(institutionName, null, null);
        Institution institution = new Institution(institutionId, institutionName, null, null);
        when(institutionsRepository.partiallyUpdateInstitution(institutionUpdate, institutionId)).thenReturn(institution);

        //when
        Institution expectedInstitution = institutionsService.partiallyUpdateInstitution(institutionUpdate, institutionId);

        //then
        verify(institutionsRepository).partiallyUpdateInstitution(institutionUpdate, institutionId);
        assertEquals(expectedInstitution, institution);


    }
}
