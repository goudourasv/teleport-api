package com.goudourasv.utils.errors;

import javax.ws.rs.ServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


//TODO handle exceptions from Jackson (e.g when missing getter ,500 )
@Provider
public class AppExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
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

            ErrorPayload errorPayload = new ErrorPayload(exception.getMessage(), status);
            return Response.status(errorPayload.getStatus()).entity(errorPayload).build();
        }

        ErrorPayload payload = new ErrorPayload("Internal Server Error", Response.Status.INTERNAL_SERVER_ERROR);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(payload.getMessage()).build();
    }
}
