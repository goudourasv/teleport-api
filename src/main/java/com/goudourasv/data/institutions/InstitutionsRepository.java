package com.goudourasv.data.institutions;

import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.http.institutions.dto.InstitutionCreate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class InstitutionsRepository {
    private final EntityManager entityManager;

    public InstitutionsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Institution> getInstitutions() {
        String sqlQuery = "SELECT * FROM institutions";
        List<Institution> institutions = new ArrayList<>();
        @SuppressWarnings("unchecked") //java generics
        List<InstitutionEntity> institutionEntities = entityManager.createNativeQuery(sqlQuery, InstitutionEntity.class).getResultList();
        for (InstitutionEntity institutionEntity : institutionEntities) {
            Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
            institutions.add(institution);
        }
        return institutions;
    }







    public Institution createInstitution(InstitutionCreate institutionCreate) {
        InstitutionEntity institutionEntity = new InstitutionEntity();
        institutionEntity.setName(institutionCreate.getName());
        institutionEntity.setCity(institutionCreate.getCity());
        institutionEntity.setCountry(institutionCreate.getCountry());
        entityManager.persist(institutionEntity);
        entityManager.flush();
        Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
        return institution;

    }

    public Institution getSpecificInstitution(UUID id) {
        InstitutionEntity institutionEntity = entityManager.find(InstitutionEntity.class, id);
        Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
        return institution;
    }

    public boolean deleteSpecificInstitution(UUID id) {
        String sqlQuery = "DELETE FROM institutions WHERE id = :id";
        entityManager.createNativeQuery(sqlQuery,InstitutionEntity.class).setParameter("id",id).executeUpdate();
        return true;



    }
}
