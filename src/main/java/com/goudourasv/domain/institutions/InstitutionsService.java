package com.goudourasv.domain.institutions;

import com.goudourasv.data.institutions.InstitutionsRepository;
import com.goudourasv.http.institutions.dto.InstitutionCreate;
import com.goudourasv.http.institutions.dto.InstitutionUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.*;

@ApplicationScoped
public class InstitutionsService {
    private InstitutionsRepository institutionsRepository;
    Map<UUID, Institution> institutionsMap = new HashMap<>();

    public InstitutionsService(InstitutionsRepository institutionsRepository) {
        Institution stanford = new Institution(UUID.fromString("cdf2b504-ad7c-46e4-bd01-7e7040ed3052"), "stanford", "USA", "California");
        Institution auth = new Institution(UUID.fromString("d5c83909-c699-40b5-ac04-c65b558a16c3"), "auth", "Greece", "Thessaloniki");
        Institution buddha = new Institution(UUID.fromString("cc249d1c-c001-4140-ab0d-1b25e7d64f42"), "buddha", "Nepal", "Kathmandu");
        institutionsMap.put(stanford.getId(), stanford);
        institutionsMap.put(auth.getId(), auth);
        institutionsMap.put(buddha.getId(), buddha);
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
        boolean deleted= institutionsRepository.deleteSpecificInstitution(id);
        return deleted;
    }

    public Institution replaceInstitution(InstitutionCreate input, UUID id) {
        Institution institution = new Institution(id, input.getName(), input.getCountry(), input.getCity());
        institutionsMap.put(institution.getId(), institution);
        return institution;

    }

    public Institution partiallyUpdateInstitution(InstitutionUpdate input, UUID id) {
        Institution institutionToUpdate = institutionsMap.get(id);

        if (input.getName() != null) {
            String newInstitutionName = input.getName();
            institutionToUpdate.setName(newInstitutionName);
        }
        if (input.getCountry() != null) {
            String newInstitutionCountry = input.getCountry();
            institutionToUpdate.setCountry(newInstitutionCountry);
        }
        if (input.getCity() != null) {
            String newInstitutionCity = input.getCity();
            institutionToUpdate.setCity(newInstitutionCity);
        }

        return institutionToUpdate;
    }


}
