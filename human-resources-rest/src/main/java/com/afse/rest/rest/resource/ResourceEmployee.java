package com.afse.rest.rest.resource;

import com.afse.persistence.entity.Employee;
import com.afse.rest.boundary.BoundaryEmployee;
import exception.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * End points of CRUD for department, is used (Consumes-Produces) json format
 */
@Path("/employee")
public class ResourceEmployee {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceEmployee.class);

    @EJB
    private BoundaryEmployee boundaryEmployee;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveEmployee(Employee employee) throws Exception {

        LOGGER.debug("Post employee was called (url : /resource/employee/save");

        Employee newEmployee = boundaryEmployee.save(employee);

        return Response.ok(newEmployee).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee employee) throws Exception {

        LOGGER.debug("Put employee was called (url : /resource/employee/");

        Employee newEmployee = boundaryEmployee.update(employee);

        return Response.ok(newEmployee).status(Response.Status.NO_CONTENT).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees() {

        LOGGER.debug("Get all employee was called(url : /resource/employee/all");

        List<Employee> employees = boundaryEmployee.findAll();

        return Response.ok(employees).build();

    }

    @GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findEmployee(@PathParam("id") Long id) throws InvalidInputException {

        LOGGER.debug("Get employee was called (url : /resource/employee/" + id);

        Employee newEmployee = boundaryEmployee.find(id);

        return Response.ok(newEmployee).build();

    }

    @DELETE
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("id") Long id) throws InvalidInputException {

        LOGGER.debug("Delete employee was called (url : /resource/employee/" + id);

        boundaryEmployee.delete(id);

        return Response.ok().build();

    }


}
