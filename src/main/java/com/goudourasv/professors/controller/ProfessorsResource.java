package com.goudourasv.professors.controller;

import com.goudourasv.professors.controller.dto.ProfessorCreate;
import com.goudourasv.professors.controller.dto.ProfessorUpdate;
import com.goudourasv.professors.domain.Professor;
import com.goudourasv.professors.service.ProfessorsService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/professors")
public class ProfessorsResource {
    private final ProfessorsService professorsService = new ProfessorsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Professor> getProfessors(@QueryParam("institution") String institution) {
        List<Professor> professorsList = professorsService.getProfessors();
        List<Professor> filteredProfessorsList = professorsService.getFilteredProfessors(professorsList, institution);
        return professorsList;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor getSpecificProfessor(@PathParam("id") UUID id) {
        Professor specificProfessor = professorsService.getSpecificProfessor(id);
        return specificProfessor;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProfessor(@Valid ProfessorCreate input, UriInfo uriInfo) {
        Professor createdProfessor = professorsService.createNewProfessor(input);

        String path = uriInfo.getPath();
        String location = path + createdProfessor.getId().toString();
        return Response.created(URI.create(location)).entity(createdProfessor).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteSpecificProfessor(@PathParam("id") UUID id) {
        professorsService.deleteSpecificProfessor(id);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Professor updateProfessor(@PathParam("id") UUID id, @Valid ProfessorCreate professor) {
        Professor updatedProfessor = professorsService.replaceProfessor(id, professor);
        return updatedProfessor;
    }

    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Professor partiallyUpdateProfessor(@PathParam("id") UUID id, ProfessorUpdate input) {
        Professor professorToUpdate = professorsService.partiallyUpdateProfessor(input, id);
        return professorToUpdate;
    }


}
