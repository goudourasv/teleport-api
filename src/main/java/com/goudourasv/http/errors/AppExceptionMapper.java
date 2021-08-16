package com.goudourasv.http.errors;

import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


public class AppExceptionMapper {
    @ServerExceptionMapper
    public Response mapNotFoundException(NotFoundException e) {
        return mapException(e);
    }

    @ServerExceptionMapper
    public Response mapException(Exception exception) {
        boolean isWebAppException = exception instanceof WebApplicationException;
        boolean isServerErrorException = exception instanceof ServerErrorException;

        System.out.println(exception.getMessage());

        if (isWebAppException) {
            Response response = ((WebApplicationException) exception).getResponse();
            Response.Status status = Response.Status.fromStatusCode(response.getStatus());

            if (isServerErrorException) {
                ErrorPayload errorPayload = new ErrorPayload("Something went wrong", status);
                return Response.status(errorPayload.getStatus()).entity(errorPayload).build();
            }

            // ClientErrorException (4xx)
            ErrorPayload errorPayload = new ErrorPayload(exception.getMessage(), status);
            return Response.status(errorPayload.getStatus()).entity(errorPayload).build();
        }

        ErrorPayload payload = new ErrorPayload("Something went wrong", Response.Status.INTERNAL_SERVER_ERROR);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(payload).build();
    }
}
