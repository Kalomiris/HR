package com.afse.rest.mapper;

import exception.InvalidInputException;

import javax.ejb.EJBException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {

        if (e instanceof EJBException) {
            if (e.getCause() instanceof ConstraintViolationException) {
                return Response.status(Response.Status.BAD_REQUEST).entity(((ConstraintViolationException) (e.getCause())).getConstraintViolations()).build();
            }
        }

        if (e instanceof InvalidInputException) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }

}

