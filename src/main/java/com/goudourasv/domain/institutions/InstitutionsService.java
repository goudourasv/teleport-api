package com.goudourasv.domain.institutions;

import com.goudourasv.data.institutions.InstitutionsRepository;
import com.goudourasv.http.institutions.dto.InstitutionCreate;
import com.goudourasv.http.institutions.dto.InstitutionUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class InstitutionsService {
    private InstitutionsRepository institutionsRepository;

    public InstitutionsService(InstitutionsRepository institutionsRepository) {
        this.institutionsRepository = institutionsRepository;
    }


    public List<Institution> getInstitutions() {
        List<Institution> institutions = institutionsRepository.getInstitutions();
        return institutions;
    }

    public List<Institution> getFilteredList(List<Institution> institutionList, String country, String city) {
        List<Institution> filteredList = new ArrayList<>();
        if (country == null && city == null) {
            filteredList.addAll(institutionList);
        } else {
            for (Institution institution : institutionList) {
                boolean isMatch = true;

                if (country != null && !institution.getCountry().equals(country)) {
                    isMatch = false;
                }

                if (city != null && !institution.getCity().equals(city)) {
                    isMatch = false;
                }
                if (isMatch == true) {
                    filteredList.add(institution);
                }
            }
        }
        return filteredList;
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
