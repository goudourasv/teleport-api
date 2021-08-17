package com.goudourasv.data.institutions;


import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.http.institutions.dto.InstitutionCreate;
import com.goudourasv.http.institutions.dto.InstitutionUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.goudourasv.data.institutions.InstitutionsMapper.toInstitution;
import static com.goudourasv.data.institutions.InstitutionsMapper.toInstitutions;

@ApplicationScoped
public class InstitutionsRepository {
    private final EntityManager entityManager;

    public InstitutionsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Institution> getInstitutions(String country, String city) {
        String sqlQuery = "SELECT * FROM institutions";
        if (country != null || city != null) {
            sqlQuery += " WHERE ";
        }

        Map<String, Object> parametersMap = new HashMap<>();

        boolean isFirst = true;
        if (country != null) {
            if (isFirst) {
                sqlQuery += "country = :country";
                isFirst = false;
            } else {
                sqlQuery += " AND country = :country";
            }
            parametersMap.put("country", country);
        }
        if (city != null) {
            if (isFirst) {
                sqlQuery += "city = :city";
                isFirst = false;
            } else {
                sqlQuery += " AND city = :city";
            }
            parametersMap.put("city", city);
        }

        Query query = entityManager.createNativeQuery(sqlQuery, InstitutionEntity.class);
        for (String key : parametersMap.keySet()) {
            query.setParameter(key, parametersMap.get(key));
        }

        @SuppressWarnings("unchecked")
        List<InstitutionEntity> institutionEntities = query.getResultList();
        List<Institution> institutions = toInstitutions(institutionEntities);

        return institutions;
    }

    public Institution getSpecificInstitution(UUID id) {
        InstitutionEntity institutionEntity = entityManager.find(InstitutionEntity.class, id);
        if(institutionEntity == null){
            return null;
        }
        Institution institution = toInstitution(institutionEntity);

        return institution;
    }


    public Institution createInstitution(InstitutionCreate institutionCreate) {
        InstitutionEntity institutionEntity = new InstitutionEntity();
        institutionEntity.setName(institutionCreate.getName());
        institutionEntity.setCity(institutionCreate.getCity());
        institutionEntity.setCountry(institutionCreate.getCountry());

        entityManager.persist(institutionEntity);
        entityManager.flush();

        Institution institution = toInstitution(institutionEntity);
        return institution;
    }


    public boolean deleteSpecificInstitution(UUID id) {
        InstitutionEntity institutionEntity = entityManager.getReference(InstitutionEntity.class, id);
        if (institutionEntity == null) {
            return false;
        }
        entityManager.remove(institutionEntity);
        return true;

    }

    public Institution replaceInstitution(InstitutionCreate institutionCreate, UUID id) {
        InstitutionEntity institutionEntity = entityManager.getReference(InstitutionEntity.class, id);
        institutionEntity.setId(id);
        institutionEntity.setName(institutionCreate.getName());
        institutionEntity.setCountry(institutionCreate.getCountry());
        institutionEntity.setCity(institutionCreate.getCity());
        try {
            entityManager.merge(institutionEntity);
        } catch (Exception ex) {
            throw new NotFoundException("Institution with id: " + id + "doesn't exist");
        }

        Institution institution = toInstitution(institutionEntity);
        return institution;
    }

    public Institution partiallyUpdateInstitution(InstitutionUpdate institutionUpdate, UUID id) {
        InstitutionEntity institutionEntity = entityManager.getReference(InstitutionEntity.class, id);

        if (institutionUpdate.getName() != null) {
            String newInstitutionName = institutionUpdate.getName();
            institutionEntity.setName(newInstitutionName);
        }
        if (institutionUpdate.getCountry() != null) {
            String newInstitutionCountry = institutionUpdate.getCountry();
            institutionEntity.setCountry(newInstitutionCountry);
        }
        if (institutionUpdate.getCity() != null) {
            String newInstitutionCity = institutionUpdate.getCity();
            institutionEntity.setCity(newInstitutionCity);
        }
        entityManager.merge(institutionEntity);
        entityManager.flush();

        Institution institution = toInstitution(institutionEntity);
        return institution;
    }
}
