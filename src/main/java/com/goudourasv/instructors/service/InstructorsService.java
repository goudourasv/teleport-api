package com.goudourasv.instructors.service;

import com.goudourasv.instructors.controller.dto.InstructorCreate;
import com.goudourasv.instructors.controller.dto.InstructorUpdate;
import com.goudourasv.instructors.domain.Instructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class InstructorsService {
    private final HashMap<UUID, Instructor> instructorsMap = new HashMap<>();

    public InstructorsService() {
        Instructor mehranSahami = new Instructor(UUID.fromString("86664d56-71c6-4de6-9771-cb8e707c8674"), "Mehran", "Sahami", "Stanford");
        Instructor jabbourNikolaos = new Instructor(UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83"), "Nikolaos", "Jabbour", "Auth");
        Instructor lilaNikolaou = new Instructor(UUID.fromString("daad559f-4755-4d8a-9d3c-5e039e2ceb2f"), "Lila", "Nikolaou", "Buddha");
        instructorsMap.put(mehranSahami.getId(), mehranSahami);
        instructorsMap.put(jabbourNikolaos.getId(), jabbourNikolaos);
        instructorsMap.put(lilaNikolaou.getId(), lilaNikolaou);
    }

    public List<Instructor> getInstructors() {
        List<Instructor> instructorList = new ArrayList<>(instructorsMap.values());
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

    public Instructor getSpecificInstructor(UUID id) {
        Instructor specificInstructor = instructorsMap.get(id);
        return specificInstructor;

    }

    public Instructor createNewInstructor(InstructorCreate input) {
        Instructor instructor = new Instructor(input.getFirstName(), input.getLastName(), input.getInstitution());
        instructorsMap.put(instructor.getId(), instructor);
        return instructor;

    }

    public void deleteSpecificInstructor(UUID id) {
        instructorsMap.remove(id);
    }


    public Instructor replaceInstructor(UUID id, InstructorCreate input) {
        Instructor instructor = new Instructor(id, input.getFirstName(), input.getLastName(), input.getInstitution());
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
            String newInstructorInstitution = input.getInstitution();
            instructor.setInstitution(newInstructorInstitution);
        }
        return instructor;
    }

}
