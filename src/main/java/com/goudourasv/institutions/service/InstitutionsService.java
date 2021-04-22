package com.goudourasv.institutions.service;

import com.goudourasv.institutions.controller.dto.InstitutionCreate;
import com.goudourasv.institutions.domain.Institution;

import java.util.*;

public class InstitutionsService {
    Map<UUID,Institution> institutionsMap = new HashMap<>();

    public InstitutionsService(){
        Institution Stanford = new Institution(UUID.fromString("cdf2b504-ad7c-46e4-bd01-7e7040ed3052"),"stanford","USA","California");
        Institution AUTH = new Institution(UUID.fromString("d5c83909-c699-40b5-ac04-c65b558a16c3"),"auth","Greece","Thessaloniki");
        Institution Buddha = new Institution(UUID.fromString( "cc249d1c-c001-4140-ab0d-1b25e7d64f42"),"buddha","Nepal","Kathmandu");
        institutionsMap.put(Stanford.getId(),Stanford);
        institutionsMap.put(AUTH.getId(),AUTH);
        institutionsMap.put(Buddha.getId(),Buddha);

    }
    public List<Institution> getInstitutions(){
        List<Institution> institutions = new ArrayList<>(institutionsMap.values());
        return institutions;
    }


    public Institution getSpecificInstitution(UUID id) {
        Institution specificInstitution = institutionsMap.get(id);
        return specificInstitution;
    }
    public Institution createInstitution(InstitutionCreate institutionInput){
        Institution institution = new Institution(institutionInput.getName(),institutionInput.getCountry(),institutionInput.getCity());
        institution.generateNewId();
        institutionsMap.put(institution.getId(),institution);
        return institution;
    }

    public void deleteSpecificCourse(UUID id) {
        institutionsMap.remove(id);
    }
}
