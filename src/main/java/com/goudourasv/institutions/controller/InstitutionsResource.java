package com.goudourasv.institutions.controller;

import com.goudourasv.institutions.controller.dto.InstitutionCreate;
import com.goudourasv.institutions.domain.Institution;
import com.goudourasv.institutions.service.InstitutionsService;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.Response.created;
import static javax.ws.rs.core.Response.status;

@Path("/institutions")
public class InstitutionsResource {
   private InstitutionsService institutionsService = new InstitutionsService();

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List<Institution> getInstitutions(){
      List<Institution> institutions = institutionsService.getInstitutions();
      return institutions;
   }


   @GET
   @Path("{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Institution getSpecificInstitution(@PathParam("id")UUID id){
      Institution institution = institutionsService.getSpecificInstitution(id);
      return institution;
   }


   @POST
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response createNewInstitution(InstitutionCreate institution, UriInfo uriInfo){
     Institution createdInstitution = institutionsService.createInstitution(institution);
      URI uri = uriInfo.getRequestUri();
      return Response.created(uri).entity(createdInstitution).build();
   }


   @DELETE
   @Path("/{id}")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public void deleteInstitution(@PathParam("id") UUID id){
      institutionsService.deleteSpecificCourse(id);

   }

   @PUT
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Institution replaceInstitution(){

   }
}
