package com.goudourasv.http.institutions

import com.goudourasv.domain.institutions.Institution
import com.goudourasv.domain.institutions.InstitutionsService
import com.goudourasv.http.institutions.dto.InstitutionCreate
import com.goudourasv.http.institutions.dto.InstitutionUpdate
import io.smallrye.common.annotation.Blocking
import java.net.URI
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@ApplicationScoped
@Path("/institutions")
class InstitutionsResource(private val institutionsService: InstitutionsService) {
    @Blocking
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getInstitutions(
        @QueryParam("country") country: String?,
        @QueryParam("city") city: String?
    ): List<Institution> {
        return institutionsService.getFilteredInstitutions(country, city)
    }

    @Blocking
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getSpecificInstitution(@PathParam("id") id: UUID): Institution {
        return institutionsService.getSpecificInstitution(id) ?: throw NotFoundException()
    }

    @Blocking
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createNewInstitution(@Valid institution: InstitutionCreate, uriInfo: UriInfo): Response {
        val createdInstitution = institutionsService.createInstitution(institution)
        val path = uriInfo.path
        val location = path + createdInstitution.id
        return Response.created(URI.create(location)).entity(createdInstitution).build()
    }

    @Blocking
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteInstitution(@PathParam("id") id: UUID) {
        val deleted = institutionsService.deleteSpecificInstitution(id)
        if (!deleted) {
            throw NotFoundException("Institution with id: $id doesn't exist")
        }
    }

    @Blocking
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateInstitution(@PathParam("id") id: UUID, @Valid input: InstitutionCreate): Institution {
        return institutionsService.replaceInstitution(id, input)
    }

    @Blocking
    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun partiallyUpdateInstitution(@PathParam("id") id: UUID, input: InstitutionUpdate): Institution {
        return institutionsService.partiallyUpdateInstitution(id, input)
    }
}