package com.goudourasv.http.tags

import com.goudourasv.domain.tags.Tag
import com.goudourasv.domain.tags.TagsService
import com.goudourasv.http.tags.dto.TagCreate
import io.smallrye.common.annotation.Blocking
import java.net.URI
import javax.enterprise.context.ApplicationScoped
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@ApplicationScoped
@Path("/tags")
class TagsResource(private val tagsService: TagsService) {


    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Blocking
    fun getTags(): List<Tag> {
        return tagsService.tagsList
    }

    @Blocking
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createTag(input: @Valid TagCreate?, uriInfo: UriInfo): Response {
        val createdTag = tagsService.createNewTag(input)
        val path = uriInfo.path
        val location = path + "/" + createdTag.name
        return Response.created(URI.create(location)).entity(createdTag).build()
    }

    @Blocking
    @DELETE
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun deleteTag(@PathParam("name") name: String?) {
        tagsService.deleteSpecificTag(name)
    }
}