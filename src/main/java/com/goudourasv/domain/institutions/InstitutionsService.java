package com.goudourasv.domain.institutions;

import com.goudourasv.data.institutions.InstitutionsRepository;
import com.goudourasv.http.institutions.dto.InstitutionCreate;
import com.goudourasv.http.institutions.dto.InstitutionUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class InstitutionsService {
    private InstitutionsRepository institutionsRepository;

    public InstitutionsService(InstitutionsRepository institutionsRepository) {
        this.institutionsRepository = institutionsRepository;
    }

    @Transactional
    public List<Institution> getFilteredInstitutions(String country, String city) {
        List<Institution> filteredInstitutions = institutionsRepository.getInstitutions(country, city);
        return filteredInstitutions;
    }


    @Transactional
    public Institution getSpecificInstitution(UUID id) {
        Institution specificInstitution = institutionsRepository.getSpecificInstitution(id);
        return specificInstitution;
    }

    @Transactional
    public Institution createInstitution(InstitutionCreate institutionCreate) {
        Institution institution = institutionsRepository.createInstitution(institutionCreate);
        return institution;
    }

    @Transactional
    public boolean deleteSpecificCourse(UUID id) {
        boolean deleted = institutionsRepository.deleteSpecificInstitution(id);
        return deleted;
    }

    @Transactional
    public Institution replaceInstitution(InstitutionCreate institutionCreate, UUID id) {
        Institution updatedInstitution = institutionsRepository.replaceInstitution(institutionCreate, id);
        return updatedInstitution;
    }

    @Transactional
    public Institution partiallyUpdateInstitution(InstitutionUpdate institutionUpdate, UUID id) {

        Institution updatedInstitution = institutionsRepository.partiallyUpdateInstitution(institutionUpdate, id);
        return updatedInstitution;
    }


}
