package com.goudourasv.institutions.controller;

import com.goudourasv.institutions.service.InstitutionsService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/institutions")
public class InstitutionsResource {
   private InstitutionsService institutionsService = new InstitutionsService();

   @GET
   @Produces(MediaType.APPLICATION_JSON)

}
