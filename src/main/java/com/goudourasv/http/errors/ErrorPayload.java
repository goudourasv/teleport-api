package com.goudourasv.http.errors;

import javax.ws.rs.core.Response;

public class ErrorPayload {
    private String message;
    private Response.Status status;


    public ErrorPayload(String message, Response.Status status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Response.Status getStatus() {
        return status;
    }

}
