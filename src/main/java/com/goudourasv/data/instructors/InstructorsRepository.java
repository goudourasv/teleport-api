package com.goudourasv.data.instructors;


import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.http.instructors.dto.InstructorCreate;

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


    public List<Instructor> getInstructors(){
        String sqlQuery = "SELECT * FROM instructors";
        List<Instructor> instructors = new ArrayList<>();

        @SuppressWarnings("unchecked")//java generics
        List<InstructorEntity> instructorEntities = entityManager.createNativeQuery(sqlQuery,InstructorEntity.class).getResultList();

        for(InstructorEntity instructorEntity : instructorEntities){
            Instructor instructor = new Instructor(instructorEntity.getId(),instructorEntity.getFirstName(),instructorEntity.getLastName(),null);
            instructors.add(instructor);
        }
        return instructors;
    }

    public Instructor getSpecificInstructor(UUID id){
        InstructorEntity instructorEntity =entityManager.find(InstructorEntity.class,id);
        Instructor instructor = new Instructor(instructorEntity.getId(),instructorEntity.getFirstName(),instructorEntity.getLastName(),null);
        return instructor;

    }

    public Instructor createNewInstructor(InstructorCreate instructorCreate){
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setFirstName(instructorCreate.getFirstName());
        instructorEntity.setLastName(instructorCreate.getLastName());
        entityManager.persist(instructorEntity);
        entityManager.flush();
        Instructor instructor = new Instructor(instructorEntity.getId(),instructorEntity.getFirstName(),instructorEntity.getLastName(),null);
        return instructor;



    }
}
