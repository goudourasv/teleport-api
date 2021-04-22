package com.goudourasv.institutions.controller;

import com.goudourasv.institutions.controller.dto.InstitutionCreate;
import com.goudourasv.institutions.controller.dto.InstitutionUpdate;
import com.goudourasv.institutions.domain.Institution;
import com.goudourasv.institutions.service.InstitutionsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.UUID;


@Path("/institutions")
public class InstitutionsResource {
    private InstitutionsService institutionsService = new InstitutionsService();
    //TODO Filtering

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Institution> getInstitutions() {
        List<Institution> institutions = institutionsService.getInstitutions();
        return institutions;
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Institution getSpecificInstitution(@PathParam("id") UUID id) {
        Institution institution = institutionsService.getSpecificInstitution(id);
        return institution;
    }

    //TODO change Location header
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewInstitution(InstitutionCreate institution, UriInfo uriInfo) {
        Institution createdInstitution = institutionsService.createInstitution(institution);
        URI uri = uriInfo.getRequestUri();
        return Response.created(uri).entity(createdInstitution).build();
    }


    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteInstitution(@PathParam("id") UUID id) {
        institutionsService.deleteSpecificCourse(id);

    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Institution replaceInstitution(@PathParam("id") UUID id, InstitutionUpdate input) {
        Institution replacedInstitution = institutionsService.replaceInstitution(input, id);
        return replacedInstitution;
    }

    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Institution updateInstitution(@PathParam("id") UUID id, InstitutionUpdate input) {
        Institution updatedInstitution = institutionsService.partiallyUpdateInstitution(input, id);
        return updatedInstitution;
    }
}
