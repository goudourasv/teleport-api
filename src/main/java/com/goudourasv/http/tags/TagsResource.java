package com.goudourasv.http.tags;

import com.goudourasv.domain.tags.Tag;
import com.goudourasv.domain.tags.TagsService;
import com.goudourasv.http.tags.dto.TagCreate;
import io.smallrye.common.annotation.Blocking;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;


@Path("/tags")
public class TagsResource {
    private final TagsService tagsService;

    public TagsResource(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @Blocking
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> getTags() {
        List<Tag> tagsList = tagsService.getTagsList();
        return tagsList;
    }

    @Blocking
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTag(@Valid TagCreate input, UriInfo uriInfo) {
        Tag createdTag = tagsService.createNewTag(input);
        String path = uriInfo.getPath();
        String location = path + "/" + createdTag.getName();
        return Response.created(URI.create(location)).entity(createdTag).build();
    }

    @Blocking
    @DELETE
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteTag(@PathParam("name") String name) {
        tagsService.deleteSpecificTag(name);
    }
}
