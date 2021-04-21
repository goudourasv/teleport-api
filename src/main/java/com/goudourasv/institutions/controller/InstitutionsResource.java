package com.goudourasv.institutions.controller;

import com.goudourasv.institutions.domain.Institution;
import com.goudourasv.institutions.service.InstitutionsService;

import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

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

}
