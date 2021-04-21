package com.goudourasv;

import com.goudourasv.tags.Tag;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello-resteasy")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Tag hello() {
        Tag physics = new Tag("physics");
        return physics;
    }


}