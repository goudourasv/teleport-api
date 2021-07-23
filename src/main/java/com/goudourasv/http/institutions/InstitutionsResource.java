package com.goudourasv.http.institutions;

import com.goudourasv.domain.institutions.Institution;
import com.goudourasv.domain.institutions.InstitutionsService;
import com.goudourasv.http.institutions.dto.InstitutionCreate;
import com.goudourasv.http.institutions.dto.InstitutionUpdate;
import io.smallrye.common.annotation.Blocking;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Path("/institutions")
public class InstitutionsResource {

    private InstitutionsService institutionsService;

    public InstitutionsResource(InstitutionsService institutionsService) {
        this.institutionsService = institutionsService;

    }

    @Blocking
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Institution> getInstitutions(@QueryParam("country") String country, @QueryParam("city") String city) {
        try {
            List<Institution> filteredInstitutions = institutionsService.getFilteredInstitutions(country, city);
            return filteredInstitutions;
        } catch (Exception ex) {
            throw new ServerErrorException("Something went wrong", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Blocking
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Institution getSpecificInstitution(@PathParam("id") UUID id) {
        Institution institution = institutionsService.getSpecificInstitution(id);
        return institution;
    }

    @Blocking
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewInstitution(@Valid InstitutionCreate institution, UriInfo uriInfo) {
        Institution createdInstitution = institutionsService.createInstitution(institution);

        String path = uriInfo.getPath();
        String location = path + createdInstitution.getId().toString();
        return Response.created(URI.create(location)).entity(createdInstitution).build();
    }

    @Blocking
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteInstitution(@PathParam("id") UUID id) {
        boolean deleted = institutionsService.deleteSpecificCourse(id);
        if (!deleted) {
            throw new NotFoundException("Course with id: " + id + "doesn't exist");
        }
    }

    @Blocking
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Institution updateInstitution(@PathParam("id") UUID id, @Valid InstitutionCreate input) {
        Institution updatedInstitution = institutionsService.replaceInstitution(input, id);
        return updatedInstitution;
    }

    @Blocking
    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Institution partiallyUpdateInstitution(@PathParam("id") UUID id, InstitutionUpdate input) {
        Institution updatedInstitution = institutionsService.partiallyUpdateInstitution(input, id);
        return updatedInstitution;
    }
}
