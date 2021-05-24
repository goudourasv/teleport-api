package com.goudourasv.http.instructors;

import com.goudourasv.http.instructors.dto.InstructorCreate;
import com.goudourasv.http.instructors.dto.InstructorUpdate;
import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.instructors.InstructorsService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/instructors")
public class InstructorsResource {
    private final InstructorsService instructorsService = new InstructorsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Instructor> getInstructors(@QueryParam("institution") String institution) {
        List<Instructor> instructorsList = instructorsService.getInstructors();
        List<Instructor> filteredInstructorsList = instructorsService.getFilteredInstructors(instructorsList, institution);
        return instructorsList;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Instructor getSpecificInstructor(@PathParam("id") UUID id) {
        Instructor specificInstructor = instructorsService.getSpecificInstructor(id);
        return specificInstructor;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInstructor(@Valid InstructorCreate input, UriInfo uriInfo) {
        Instructor createdInstructor = instructorsService.createNewInstructor(input);

        String path = uriInfo.getPath();
        String location = path + createdInstructor.getId().toString();
        return Response.created(URI.create(location)).entity(createdInstructor).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteSpecificInstructor(@PathParam("id") UUID id) {
        instructorsService.deleteSpecificInstructor(id);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Instructor updateInstructor(@PathParam("id") UUID id, @Valid InstructorCreate instructor) {
        Instructor updatedInstructor = instructorsService.replaceInstructor(id, instructor);
        return updatedInstructor;
    }

    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Instructor partiallyUpdateInstructor(@PathParam("id") UUID id, InstructorUpdate input) {
        Instructor instructorToUpdate = instructorsService.partiallyUpdateInstructor(input, id);
        return instructorToUpdate;
    }


}
