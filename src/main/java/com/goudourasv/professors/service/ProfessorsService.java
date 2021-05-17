package com.goudourasv.professors.service;

import com.goudourasv.professors.controller.dto.ProfessorCreate;
import com.goudourasv.professors.controller.dto.ProfessorUpdate;
import com.goudourasv.professors.domain.Professor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ProfessorsService {
    private final HashMap<UUID, Professor> professorsMap = new HashMap<>();

    public ProfessorsService() {
        Professor mehranSahami = new Professor(UUID.fromString("86664d56-71c6-4de6-9771-cb8e707c8674"), "Mehran", "Sahami", "Stanford");
        Professor jabbourNikolaos = new Professor(UUID.fromString("e21be850-20f7-4943-bd37-c226cbdc8c83"), "Nikolaos", "Jabbour", "Auth");
        Professor lilaNikolaou = new Professor(UUID.fromString("daad559f-4755-4d8a-9d3c-5e039e2ceb2f"), "Lila", "Nikolaou", "Buddha");
        professorsMap.put(mehranSahami.getId(), mehranSahami);
        professorsMap.put(jabbourNikolaos.getId(), jabbourNikolaos);
        professorsMap.put(lilaNikolaou.getId(), lilaNikolaou);
    }

    public List<Professor> getProfessors() {
        List<Professor> professorList = new ArrayList<>(professorsMap.values());
        return professorList;
    }

    public List<Professor> getFilteredProfessors(List<Professor> professorsList, String institution) {
        List<Professor> filteredProfessorList = new ArrayList<>();

        if (institution == null) {
            filteredProfessorList.addAll(professorsList);
        } else {
            for (Professor professor : professorsList) {
                if (institution != null && professor.getInstitution().equals(institution)) {
                    continue;
                }
                filteredProfessorList.add(professor);
            }
        }
        return filteredProfessorList;
    }

    public Professor getSpecificProfessor(UUID id) {
        Professor specificProfessor = professorsMap.get(id);
        return specificProfessor;

    }

    public Professor createNewProfessor(ProfessorCreate input) {
        Professor professor = new Professor(input.getFirstName(), input.getLastName(), input.getInstitution());
        professorsMap.put(professor.getId(), professor);
        return professor;

    }

    public void deleteSpecificProfessor(UUID id) {
        professorsMap.remove(id);
    }


    public Professor replaceProfessor(UUID id, ProfessorCreate input) {
        Professor professor = new Professor(id, input.getFirstName(), input.getLastName(), input.getInstitution());
        professorsMap.replace(professor.getId(), professor);
        return professor;

    }

    public Professor partiallyUpdateProfessor(ProfessorUpdate input, UUID id) {
        Professor professor = professorsMap.get(id);

        if (input.getFirstName() != null) {
            String newProfessorFirstName = input.getFirstName();
            professor.setFirstName(newProfessorFirstName);
        }
        if (input.getLastName() != null) {
            String newProfessorLastName = input.getLastName();
            professor.setLastName(newProfessorLastName);
        }
        if (input.getInstitution() != null) {
            String newProfessorInstitution = input.getInstitution();
            professor.setInstitution(newProfessorInstitution);
        }
        return professor;
    }

}
