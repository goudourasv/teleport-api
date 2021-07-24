package com.goudourasv.domain.instructors;

import com.goudourasv.data.instructors.InstructorsRepository;
import com.goudourasv.http.instructors.dto.InstructorCreate;
import com.goudourasv.http.instructors.dto.InstructorUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class InstructorsService {
    private InstructorsRepository instructorsRepository;

    public InstructorsService(InstructorsRepository instructorsRepository) {
        this.instructorsRepository = instructorsRepository;
    }

    @Transactional
    public List<Instructor> getInstructors(UUID institutionId) {
        List<Instructor> instructors = instructorsRepository.getInstructors(institutionId);
        return instructors;
    }

    @Transactional
    public Instructor getSpecificInstructor(UUID id) {
        Instructor instructor = instructorsRepository.getSpecificInstructor(id);
        return instructor;

    }

    @Transactional
    public Instructor createNewInstructor(InstructorCreate instructorCreate) {
        Instructor instructor = instructorsRepository.createNewInstructor(instructorCreate);
        return instructor;

    }

    @Transactional
    public boolean deleteSpecificInstructor(UUID id) {
        boolean deleted = instructorsRepository.deleteSpecificInstructor(id);
        return deleted;
    }

    @Transactional
    public Instructor replaceInstructor(UUID id, InstructorCreate instructorCreate) {
        Instructor instructor = instructorsRepository.replaceInstructor(id, instructorCreate);
        return instructor;

    }

    @Transactional
    public Instructor partiallyUpdateInstructor(InstructorUpdate instructorUpdate, UUID id) {
        Instructor instructor = instructorsRepository.partiallyUpdateInstructor(instructorUpdate, id);
        return instructor;
    }

}
