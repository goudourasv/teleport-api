package com.goudourasv.domain.institutions;

import com.goudourasv.data.institutions.InstitutionsRepository;
import com.goudourasv.domain.courses.CoursesService;
import com.goudourasv.domain.tags.TagsService;
import com.goudourasv.http.institutions.dto.InstitutionCreate;
import com.goudourasv.http.institutions.dto.InstitutionUpdate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static com.goudourasv.utils.TestData.*;
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
        List<Institution> institutions = createInstitutions();
        String country = "Greece";
        String city = "Thessaloniki";

        when(institutionsRepository.getInstitutions(country,city)).thenReturn(institutions);

        //when
        List<Institution> expectedInstitutions = institutionsService.getFilteredInstitutions(country,city);

        //then
        verify(institutionsRepository).getInstitutions(country,city);
        assertThat(expectedInstitutions).hasSameSizeAs(institutions).hasSameElementsAs(institutions);
    }


    @Test
    public void shouldReturnSpecificInstitution() {
        //given
        UUID institutionId = UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83");
        Institution institution = createInstitution();

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
        InstitutionCreate institutionCreate = createInstitutionCreate();
        Institution institution = createInstitution();

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
        InstitutionCreate institutionCreate = createInstitutionCreate();
        Institution institution = createInstitution();
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
        InstitutionUpdate institutionUpdate = createInstitutionUpdate();
        Institution institution = createInstitution();

        when(institutionsRepository.partiallyUpdateInstitution(institutionUpdate, institutionId)).thenReturn(institution);

        //when
        Institution expectedInstitution = institutionsService.partiallyUpdateInstitution(institutionUpdate, institutionId);

        //then
        verify(institutionsRepository).partiallyUpdateInstitution(institutionUpdate, institutionId);
        assertEquals(expectedInstitution, institution);


    }
}
