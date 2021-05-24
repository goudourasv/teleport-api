package com.goudourasv.http.institutions;

import com.goudourasv.http.institutions.dto.InstitutionCreate;
import com.goudourasv.http.institutions.dto.InstitutionUpdate;
import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.institutions.InstitutionsService;

import javax.validation.Valid;
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


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Institution> getInstitutions(@QueryParam("country") String country, @QueryParam("city") String city) {
        List<Institution> institutionList = institutionsService.getInstitutions();
        List<Institution> filteredList = institutionsService.getFilteredList(institutionList, country, city);
        return filteredList;
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Institution getSpecificInstitution(@PathParam("id") UUID id) {
        Institution institution = institutionsService.getSpecificInstitution(id);
        return institution;
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewInstitution(@Valid InstitutionCreate institution, UriInfo uriInfo) {
        Institution createdInstitution = institutionsService.createInstitution(institution);

        String path = uriInfo.getPath();
        String location = path + createdInstitution.getId().toString();
        return Response.created(URI.create(location)).entity(createdInstitution).build();
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
    public Institution updateInstitution(@PathParam("id") UUID id, @Valid InstitutionCreate input) {
        Institution updatedInstitution = institutionsService.replaceInstitution(input, id);
        return updatedInstitution;
    }

    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Institution partiallyUpdateInstitution(@PathParam("id") UUID id, InstitutionUpdate input) {
        Institution updatedInstitution = institutionsService.partiallyUpdateInstitution(input, id);
        return updatedInstitution;
    }
}
