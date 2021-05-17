package com.goudourasv.institutions.service;

import com.goudourasv.institutions.controller.dto.InstitutionCreate;
import com.goudourasv.institutions.controller.dto.InstitutionUpdate;
import com.goudourasv.institutions.domain.Institution;

import java.util.*;

public class InstitutionsService {
    Map<UUID, Institution> institutionsMap = new HashMap<>();

    public InstitutionsService() {
        Institution stanford = new Institution(UUID.fromString("cdf2b504-ad7c-46e4-bd01-7e7040ed3052"), "stanford", "USA", "California");
        Institution auth = new Institution(UUID.fromString("d5c83909-c699-40b5-ac04-c65b558a16c3"), "auth", "Greece", "Thessaloniki");
        Institution buddha = new Institution(UUID.fromString("cc249d1c-c001-4140-ab0d-1b25e7d64f42"), "buddha", "Nepal", "Kathmandu");
        institutionsMap.put(stanford.getId(), stanford);
        institutionsMap.put(auth.getId(), auth);
        institutionsMap.put(buddha.getId(), buddha);

    }

    public List<Institution> getInstitutions() {
        List<Institution> institutions = new ArrayList<>(institutionsMap.values());
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


    public Institution getSpecificInstitution(UUID id) {
        Institution specificInstitution = institutionsMap.get(id);
        return specificInstitution;
    }

    public Institution createInstitution(InstitutionCreate institutionInput) {
        Institution institution = new Institution(institutionInput.getName(), institutionInput.getCountry(), institutionInput.getCity());
        institutionsMap.put(institution.getId(), institution);

        return institution;
    }

    public void deleteSpecificCourse(UUID id) {
        institutionsMap.remove(id);
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
