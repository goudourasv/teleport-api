package com.goudourasv.data.institutions;

import com.goudourasv.domain.institutions.Institution;

import java.util.ArrayList;
import java.util.List;

public class InstitutionsMapper {
    public static Institution toInstitution(InstitutionEntity institutionEntity){
        Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
        return institution;
    }

    public static List<Institution> toInstitutions(List<InstitutionEntity> institutionEntities){
        List<Institution> institutions = new ArrayList<>();

        for (InstitutionEntity institutionEntity : institutionEntities) {
            Institution institution = toInstitution(institutionEntity);
            institutions.add(institution);
        }
        return  institutions;
    }
}
