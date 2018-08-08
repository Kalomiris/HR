package com.afse.rest.rest.resource;

import com.afse.persistence.entity.Employee;
import com.afse.rest.boundary.BoundaryEmployee;
import com.afse.service.service.EmailService;
import com.afse.service.service.EmployeeService;
import exception.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

/**
 * End points of CRUD for department, is used (Consumes-Produces) json format
 */
@Path("/employee")
public class ResourceEmployee implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceEmployee.class);
    private static final long serialVersionUID = 8286635769897649503L;

    @EJB
    private EmployeeService employeeService;

    @EJB
    private BoundaryEmployee boundaryEmployee;

    @EJB
    private EmailService emailServiceImpl;

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveEmployee(Employee employee) throws Exception {

        LOGGER.debug("Post employee was called (url : /resource/department/save");

        Employee newEmployee = boundaryEmployee.save(employee);

        emailServiceImpl.sendMail("save");

        return Response.ok().status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee employee) throws Exception {

        LOGGER.debug("Put employee was called (url : /resource/department/");

        Employee newEmployee = boundaryEmployee.update(employee);

        emailServiceImpl.sendMail("update");

        return Response.ok().status(Response.Status.NO_CONTENT).build();

    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees() {

        LOGGER.debug("Get all employee was called(url : /resource/employee/all");

        List<Employee> employees = employeeService.findAll();

        return Response.ok(employees).build();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findEmployee(@PathParam("id") Long id) throws InvalidInputException {

        LOGGER.debug("Get employee was called (url : /resource/employee/" + id);

        Employee newEmployee = boundaryEmployee.find(id);

        return Response.ok(newEmployee).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("id") Long id) throws InvalidInputException {

        LOGGER.debug("Delete employee was called (url : /resource/department/" + id);

        boundaryEmployee.delete(id);

        return Response.ok().status(Response.Status.NO_CONTENT).build();

    }


}
