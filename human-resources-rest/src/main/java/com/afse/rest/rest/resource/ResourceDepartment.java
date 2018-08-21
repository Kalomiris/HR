package com.afse.rest.rest.resource;


import com.afse.persistence.entity.Department;
import com.afse.rest.boundary.BoundaryDepartment;
import exception.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * End points of CRUD for employee, is used (Consumes-Produces) json format
 */
@Path("/department")
public class ResourceDepartment {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceDepartment.class);

    @EJB
    private BoundaryDepartment boundaryDepartment;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveDepartment(Department department) throws Exception {

        LOGGER.debug("Post department was called (url : /resource/department");

        Department newDepartment = boundaryDepartment.save(department);

        return Response.ok(newDepartment).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDepartment(Department department) throws InvalidInputException {

        LOGGER.debug("Put Department was called (url : /resource/department/");

        Department newDepartment = boundaryDepartment.update(department);

        return Response.ok(newDepartment).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartments() {

        LOGGER.debug("Get all department was called(url : /resource/department/all");

        List<Department> departments = boundaryDepartment.findAll();

        return Response.ok(departments).build();
    }

    @GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findDepartment(@PathParam("id") Long id) throws InvalidInputException {

        LOGGER.debug("Get department was called (url : /resource/department/" + id);

        Department newDepartment = boundaryDepartment.find(id);

        return Response.ok(newDepartment).build();
    }

    @DELETE
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDepartment(@PathParam("id") Long id) throws InvalidInputException {

        LOGGER.debug("Delete department was called (url : /resource/department/" + id);

        boundaryDepartment.delete(id);

        return Response.ok().build();
    }

}
