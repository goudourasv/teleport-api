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


    public Instructor replaceInstructor(UUID id, InstructorCreate instructorCreate) {
        Instructor instructor = new Instructor(id, instructorCreate.getFirstName(), instructorCreate.getLastName(), instructorCreate.getInstitution());
        instructorsMap.replace(instructor.getId(), instructor);
        return instructor;

    }

    public Instructor partiallyUpdateInstructor(InstructorUpdate input, UUID id) {
        Instructor instructor = instructorsMap.get(id);

        if (input.getFirstName() != null) {
            String newInstructorFirstName = input.getFirstName();
            instructor.setFirstName(newInstructorFirstName);
        }
        if (input.getLastName() != null) {
            String newInstructorLastName = input.getLastName();
            instructor.setLastName(newInstructorLastName);
        }
        if (input.getInstitution() != null) {
            Institution newInstructorInstitution = input.getInstitution();
            instructor.setInstitution(newInstructorInstitution);
        }
        return instructor;
    }

}
