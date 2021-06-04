package com.goudourasv.domain.instructors;

import com.goudourasv.data.instructors.InstructorsRepository;
import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.http.instructors.dto.InstructorCreate;
import com.goudourasv.http.instructors.dto.InstructorUpdate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class InstructorsService {
    private final HashMap<UUID, Instructor> instructorsMap = new HashMap<>();
    private InstructorsRepository instructorsRepository;

    public InstructorsService(InstructorsRepository instructorsRepository) {
        this.instructorsRepository = instructorsRepository;
    }
    @Transactional
    public List<Instructor> getInstructors() {
        List<Instructor> instructorList = instructorsRepository.getInstructors();
        return instructorList;
    }

    public List<Instructor> getFilteredInstructors(List<Instructor> instructorsList, String institution) {
        List<Instructor> filteredInstructorList = new ArrayList<>();

        if (institution == null) {
            filteredInstructorList.addAll(instructorsList);
        } else {
            for (Instructor instructor : instructorsList) {
                if (institution != null && instructor.getInstitution().equals(institution)) {
                    continue;
                }
                filteredInstructorList.add(instructor);
            }
        }
        return filteredInstructorList;
    }

    @Transactional
    public Instructor getSpecificInstructor(UUID id) {
        Instructor specificInstructor = instructorsRepository.getSpecificInstructor(id);
        return specificInstructor;

    }

    @Transactional
    public Instructor createNewInstructor(InstructorCreate instructorCreate) {
        Instructor instructor =instructorsRepository.createNewInstructor(instructorCreate);
        return instructor;

    }
    @Transactional
    public boolean deleteSpecificInstructor(UUID id) {
        boolean deleted = instructorsRepository.deleteSpecificInstructor(id);
        return deleted;
    }

    @Transactional
    public Instructor replaceInstructor(UUID id, InstructorCreate instructorCreate) {
        Instructor instructor = instructorsRepository.replaceInstructor(id,instructorCreate);
        return instructor;

    }

    @Transactional
    public Instructor partiallyUpdateInstructor(InstructorUpdate instructorUpdate, UUID id) {
        Instructor instructor = instructorsRepository.partiallyUpdateInstructor(instructorUpdate,id);
        return instructor;
    }

}
