package com.goudourasv.http.lectures;

import com.goudourasv.domain.lectures.Lecture;
import com.goudourasv.domain.lectures.LecturesService;
import com.goudourasv.http.lectures.dto.LectureCreate;
import com.goudourasv.http.lectures.dto.LectureUpdate;
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
    public List<Lecture> getLectures(@QueryParam("Course") UUID courseId) {
        try {
            List<Lecture> filteredLectures = lecturesService.getFilteredLectures(courseId);
            return filteredLectures;
        } catch (Exception ex) {
            throw new ServerErrorException("Something went wrong", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Blocking
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Lecture getSpecificLecture(@PathParam("id") UUID lectureId) {
        Lecture lecture = lecturesService.getSpecificLecture(lectureId);
        return lecture;
    }

    @Blocking
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLecture(@Valid LectureCreate lectureCreate, UriInfo uriInfo) {
        Lecture lecture = lecturesService.createLecture(lectureCreate);
        String path = uriInfo.getPath();
        String location = path + "/" + lecture.getId();
        return Response.created(URI.create(location)).entity(lecture).build();
    }

    @Blocking
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteLecture(@PathParam("id") UUID id) {
        boolean deleted = lecturesService.deleteSpecificLecture(id);
        if (!deleted) {
            throw new NotFoundException("Lecture with id: " + id + "doesn't exist");
        }
    }

    @Blocking
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Lecture updateLecture(@PathParam("id") UUID id, LectureCreate lectureCreate) {
        Lecture lecture = lecturesService.updateLecture(id, lectureCreate);
        return lecture;
    }

    @Blocking
    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Lecture partiallyUpdateLecture(@PathParam("id") UUID id, LectureUpdate lectureUpdate) {
        Lecture lecture = lecturesService.partiallyUpdateLecture(id, lectureUpdate);
        return lecture;

    }


}