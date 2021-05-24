package com.goudourasv.http.errors;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> setOfViolations = exception.getConstraintViolations();
        List<ConstraintViolation> listOfViolations = new ArrayList<>();
        listOfViolations.addAll(setOfViolations);

        String message = "";
        int counter = 1;

//        for (ConstraintViolation constraintViolation : listOfViolations) {
//            String result = "";
//            String violationMessage = constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage();
//
//            if (listOfViolations.size() == 1) {
//                message += violationMessage;
//            }
//
//            if (listOfViolations.size() > 1) {
//                message += violationMessage + ",";
//                counter++;
//                if (counter == listOfViolations.size() - 1) {
//                    message += violationMessage;
//                    break;
//                }
//            }
//        }

        for (ConstraintViolation constraintViolation : listOfViolations) {
            String violationMessage = constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage();

            if (counter == listOfViolations.size()) {
                message += violationMessage;
            } else {
                message += violationMessage + ", ";
            }
            counter++;
        }

        ErrorPayload errorPayload = new ErrorPayload(message, Response.Status.BAD_REQUEST);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorPayload).build();
    }
}
//            for (int i = 0; i <= listOfViolations.size(); i++) {
