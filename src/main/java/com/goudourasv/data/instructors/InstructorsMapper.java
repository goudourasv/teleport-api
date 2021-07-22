package com.goudourasv.data.instructors;

import com.goudourasv.data.institutions.InstitutionEntity;
import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.instructors.Instructor;

import java.util.ArrayList;
import java.util.List;


public class InstructorsMapper {
    public static Instructor toInstructor(InstructorEntity instructorEntity,List<InstitutionEntity> institutionEntities){
        List<Institution> institutions = new ArrayList<>();
        for (InstitutionEntity institutionEntity : institutionEntities) {
            Institution institution = new Institution(institutionEntity.getId(), institutionEntity.getName(), institutionEntity.getCountry(), institutionEntity.getCity());
            institutions.add(institution);
        }
        Instructor instructor = new Instructor(instructorEntity.getId(),instructorEntity.getFirstName(),instructorEntity.getLastName(),institutions);
        return instructor;
    }

    public static List<Instructor> toInstructors(List<InstructorEntity> instructorEntities){
        List<Instructor> instructors = new ArrayList<>();
        for (InstructorEntity instructorEntity : instructorEntities) {
            List<InstitutionEntity> institutionEntities = instructorEntity.getInstitutionEntities();
            Instructor instructor = toInstructor(instructorEntity,institutionEntities);
            instructors.add(instructor);
        }
        return instructors;
    }

}
