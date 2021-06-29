package com.goudourasv.http.lectures;

import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.domain.lectures.LecturesService;
import io.smallrye.common.annotation.Blocking;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@ApplicationScoped
@Path("/lectures")
public class LecturesResource {
    private final LecturesService lecturesService;

    // @Inject not needed in Quarkus
    public LecturesResource(LecturesService lecturesService) {
        this.lecturesService = lecturesService;
    }

    @Blocking
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Lecture> getLectures() {
        try {
            List<Lecture> filteredLectures = lecturesService.getFilteredLectures();
            return filteredLectures;
        } catch (Exception ex) {
            throw new ServerErrorException("Something went wrong", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}