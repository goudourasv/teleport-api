package com.goudourasv.data.instructors;

import com.goudourasv.data.institutions.InstitutionEntity;
import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.http.instructors.dto.InstructorCreate;
import com.goudourasv.http.instructors.dto.InstructorUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.goudourasv.data.instructors.InstructorsMapper.toInstructor;
import static com.goudourasv.data.instructors.InstructorsMapper.toInstructors;


@ApplicationScoped
public class InstructorsRepository {
    private final EntityManager entityManager;

    public InstructorsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Instructor> getInstructors(UUID institutionId) {
        Query instructorsQuery;
        if (institutionId == null) {
            String sqlQuery = "SELECT * FROM instructors";
            instructorsQuery = entityManager.createNativeQuery(sqlQuery, InstructorEntity.class);
        } else {
            String sqlQuery = "SELECT * FROM instructors JOIN institution_instructor ON instructors.id = institution_instructor.instructor_id WHERE institution_id = :institutionId";
            instructorsQuery = entityManager.createNativeQuery(sqlQuery, InstructorEntity.class).setParameter("institutionId", institutionId);
        }
        @SuppressWarnings("unchecked")
        List<InstructorEntity> instructorEntities = instructorsQuery.getResultList();
        List<Instructor> instructors = toInstructors(instructorEntities);
        return instructors;
    }


    public Instructor getSpecificInstructor(UUID id) {
        InstructorEntity instructorEntity = entityManager.find(InstructorEntity.class, id);
        List<InstitutionEntity> institutionEntities = instructorEntity.getInstitutionEntities();

        Instructor instructor = toInstructor(instructorEntity, institutionEntities);
        return instructor;

    }

    public Instructor createNewInstructor(InstructorCreate instructorCreate) {
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setFirstName(instructorCreate.getFirstName());
        instructorEntity.setLastName(instructorCreate.getLastName());
        //TODO mapping ids to institutionEntities

        List<InstitutionEntity> institutionEntities = new ArrayList<>();
        List<UUID> institutionIds = instructorCreate.getInstitutionIds();
        for (UUID id : institutionIds) {
            InstitutionEntity institutionEntity = entityManager.getReference(InstitutionEntity.class, id);
            institutionEntities.add(institutionEntity);
        }
        instructorEntity.setInstitutionEntities(institutionEntities);
        entityManager.persist(instructorEntity);
        entityManager.flush();

        Instructor instructor = toInstructor(instructorEntity,institutionEntities);
        return instructor;

    }

    public boolean deleteSpecificInstructor(UUID id) {
        InstructorEntity instructorEntity = entityManager.getReference(InstructorEntity.class, id);
        if (instructorEntity == null) {
            return false;
        }
        entityManager.remove(instructorEntity);
        return true;
    }

    public Instructor replaceInstructor(UUID id, InstructorCreate instructorCreate) {
        InstructorEntity instructorEntity = entityManager.getReference(InstructorEntity.class, id);
        instructorEntity.setFirstName(instructorCreate.getFirstName());
        instructorEntity.setLastName(instructorCreate.getLastName());


        List<InstitutionEntity> institutionEntities = new ArrayList<>();
        List<UUID> institutionIds = instructorCreate.getInstitutionIds();
        for (UUID uuid : institutionIds) {
            InstitutionEntity institutionEntity = entityManager.getReference(InstitutionEntity.class, uuid);
            institutionEntities.add(institutionEntity);
        }

        instructorEntity.setInstitutionEntities(institutionEntities);
        entityManager.merge(instructorEntity);
        entityManager.flush();

        Instructor instructor = toInstructor(instructorEntity,institutionEntities);
        return instructor;
    }

    public Instructor partiallyUpdateInstructor(InstructorUpdate instructorUpdate, UUID id) {
        InstructorEntity instructorEntity = entityManager.getReference(InstructorEntity.class, id);

        if (instructorUpdate.getFirstName() != null) {
            String newInstructorFirstName = instructorUpdate.getFirstName();
            instructorEntity.setFirstName(newInstructorFirstName);
        }
        if (instructorUpdate.getLastName() != null) {
            String newInstructorLastName = instructorUpdate.getLastName();
            instructorEntity.setLastName(newInstructorLastName);
        }
        if (instructorUpdate.getInstitutionIds() != null) {
            List<UUID> newInstitutionIds = instructorUpdate.getInstitutionIds();
            List<InstitutionEntity> institutionEntities = new ArrayList<>();
            for (UUID uuid : newInstitutionIds) {
                InstitutionEntity institutionEntity = entityManager.getReference(InstitutionEntity.class, uuid);
                institutionEntities.add(institutionEntity);
            }
            instructorEntity.setInstitutionEntities(institutionEntities);

        }

        entityManager.merge(instructorEntity);
        entityManager.flush();

        List<InstitutionEntity> institutionEntities = instructorEntity.getInstitutionEntities();
        Instructor instructor = toInstructor(instructorEntity,institutionEntities);
        
        return instructor;
    }
}

