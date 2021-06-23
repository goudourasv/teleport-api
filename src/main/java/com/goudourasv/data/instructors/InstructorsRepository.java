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
        List<Instructor> instructors = new ArrayList<>();

        for (InstructorEntity instructorEntity : instructorEntities) {
            List<Institution> institutions = new ArrayList<>();
            List<InstitutionEntity> institutionEntities = instructorEntity.getInstitutionEntities();
            for (InstitutionEntity institutionEntity : institutionEntities) {
                Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
                institutions.add(institution);
            }

            Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), institutions);
            instructors.add(instructor);
        }

        return instructors;
    }


    public Instructor getSpecificInstructor(UUID id) {
        InstructorEntity instructorEntity = entityManager.find(InstructorEntity.class, id);
        List<Institution> institutions = new ArrayList<>();
        List<InstitutionEntity> institutionEntities = instructorEntity.getInstitutionEntities();
        for (InstitutionEntity institutionEntity : institutionEntities) {
            Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
            institutions.add(institution);
        }
        Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), institutions);
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

        List<Institution> institutions = new ArrayList<>();
        for (InstitutionEntity institutionEntity : institutionEntities) {
            Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
            institutions.add(institution);
        }


        Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), institutions);
        return instructor;

    }

    public boolean deleteSpecificInstructor(UUID id) {
//        String sqlQuery = "DELETE FROM instructors WHERE id = :id";
//        int deletedEntities = entityManager.createNativeQuery(sqlQuery, InstructorEntity.class).setParameter("id", id).executeUpdate();
//        if (deletedEntities == 0) {
//            return false;
//        }
//        return true;
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

        List<Institution> institutions = new ArrayList<>();
        for (InstitutionEntity institutionEntity : institutionEntities) {
            Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
            institutions.add(institution);
        }


        Instructor instructor = new Instructor(id, instructorEntity.getFirstName(), instructorEntity.getLastName(), institutions);
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
        //TODO check in oldInstitutionList for other registrations or just delete the old and add the new one
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

        List<Institution> institutions = new ArrayList<>();
        List<InstitutionEntity> institutionEntities = instructorEntity.getInstitutionEntities();
        for (InstitutionEntity institutionEntity : institutionEntities) {
            Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
            institutions.add(institution);
        }


        Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), institutions);
        return instructor;
    }
}

