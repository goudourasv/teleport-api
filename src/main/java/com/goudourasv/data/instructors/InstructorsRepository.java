package com.goudourasv.data.instructors;


import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.http.instructors.dto.InstructorCreate;
import com.goudourasv.http.instructors.dto.InstructorUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class InstructorsRepository {
    private final EntityManager entityManager;

    public InstructorsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Instructor> getInstructors() {
        String sqlQuery = "SELECT * FROM instructors";
        List<Instructor> instructors = new ArrayList<>();

        @SuppressWarnings("unchecked")//java generics
        List<InstructorEntity> instructorEntities = entityManager.createNativeQuery(sqlQuery, InstructorEntity.class).getResultList();

        for (InstructorEntity instructorEntity : instructorEntities) {
            Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), null);
            instructors.add(instructor);
        }
        return instructors;
    }

    public Instructor getSpecificInstructor(UUID id) {
        InstructorEntity instructorEntity = entityManager.find(InstructorEntity.class, id);
        Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), null);
        return instructor;

    }

    public Instructor createNewInstructor(InstructorCreate instructorCreate) {
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setFirstName(instructorCreate.getFirstName());
        instructorEntity.setLastName(instructorCreate.getLastName());
        entityManager.persist(instructorEntity);
        entityManager.flush();
        Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), null);
        return instructor;

    }

    public boolean deleteSpecificInstructor(UUID id) {
        String sqlQuery = "DELETE FROM instructors WHERE id = :id";
        int deletedEntities = entityManager.createNativeQuery(sqlQuery, InstructorEntity.class).setParameter("id", id).executeUpdate();
        if (deletedEntities == 0) {
            return false;
        }
        return true;
    }

    public Instructor replaceInstructor(UUID id, InstructorCreate instructorCreate) {
        InstructorEntity instructorEntity = entityManager.getReference(InstructorEntity.class, id);
        instructorEntity.setFirstName(instructorCreate.getFirstName());
        instructorEntity.setLastName(instructorCreate.getLastName());
        entityManager.merge(instructorEntity);
        entityManager.flush();
        Instructor instructor = new Instructor(id, instructorEntity.getFirstName(), instructorEntity.getLastName(), null);
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

        entityManager.merge(instructorEntity);
        entityManager.flush();

        Instructor instructor = new Instructor(instructorEntity.getId(), instructorEntity.getFirstName(), instructorEntity.getLastName(), null);
        return instructor;
    }
}

