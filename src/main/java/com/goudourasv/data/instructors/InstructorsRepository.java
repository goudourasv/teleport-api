package com.goudourasv.data.instructors;


import com.goudourasv.domain.instructors.Instructor;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

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
}
