package com.goudourasv.http.instructors;

import com.goudourasv.domain.instructors.Instructor;
import com.goudourasv.domain.instructors.InstructorsService;
import com.goudourasv.http.instructors.dto.InstructorCreate;
import com.goudourasv.http.instructors.dto.InstructorUpdate;
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
@Path("/instructors")
public class InstructorsResource {
    private final InstructorsService instructorsService;

    public InstructorsResource(InstructorsService instructorsService) {
        this.instructorsService = instructorsService;
    }

    @Blocking
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Instructor> getInstructors(@QueryParam("institution") UUID institutionId) {
        List<Instructor> instructors = instructorsService.getInstructors(institutionId);
        return instructors;
    }

    @Blocking
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Instructor getSpecificInstructor(@PathParam("id") UUID id) {
        Instructor specificInstructor = instructorsService.getSpecificInstructor(id);
        return specificInstructor;
    }

    @Blocking
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInstructor(@Valid InstructorCreate instructorCreate, UriInfo uriInfo) {
        Instructor createdInstructor = instructorsService.createNewInstructor(instructorCreate);

        String path = uriInfo.getPath();
        String location = path + createdInstructor.getId().toString();
        return Response.created(URI.create(location)).entity(createdInstructor).build();
    }

    @Blocking
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteSpecificInstructor(@PathParam("id") UUID id) {
        boolean deleted = instructorsService.deleteSpecificInstructor(id);
        if (!deleted) {
            throw new NotFoundException("Instructor with id: " + id + " doesn't exist");
        }
    }

    @Blocking
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Instructor updateInstructor(@PathParam("id") UUID id, @Valid InstructorCreate instructor) {
        Instructor updatedInstructor = instructorsService.replaceInstructor(id, instructor);
        return updatedInstructor;
    }

    @Blocking
    @PATCH
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Instructor partiallyUpdateInstructor(@PathParam("id") UUID id, InstructorUpdate instructorUpdate) {
        Instructor instructorToUpdate = instructorsService.partiallyUpdateInstructor(instructorUpdate, id);
        return instructorToUpdate;
    }


}
