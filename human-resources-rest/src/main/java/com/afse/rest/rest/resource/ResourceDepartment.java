package com.afse.rest.rest.resource;


import com.afse.persistence.entity.Department;
import com.afse.rest.boundary.BoundaryDepartment;
import com.afse.service.service.DepartmentService;
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
 * End points of CRUD for employee, is used (Consumes-Produces) json format
 */
@Path("/department")
public class ResourceDepartment implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceDepartment.class);
    private static final long serialVersionUID = -7912128885297631007L;

    @EJB
    private DepartmentService departmentService;

    @EJB
    private BoundaryDepartment boundaryDepartment;

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveDepartment(Department department) throws Exception {

        LOGGER.debug("Post department was called (url : /resource/department");

        Department newDepartment = boundaryDepartment.save(department);


        return Response.ok().status(Response.Status.CREATED).build();

    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDepartment(Department department) throws InvalidInputException {

        LOGGER.debug("Put Department was called (url : /resource/department/");

        Department newDepartment = boundaryDepartment.update(department);


        return Response.ok().status(Response.Status.NO_CONTENT).build();

    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartments() {

        LOGGER.debug("Get all department was called(url : /resource/department/all");

        List<Department> departments = departmentService.findAll();

        return Response.ok(departments).build();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findDepartment(@PathParam("id") Long id) throws InvalidInputException {

        LOGGER.debug("Get department was called (url : /resource/department/" + id);

        Department newDepartment = boundaryDepartment.find(id);


        return Response.ok(newDepartment).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDepartment(@PathParam("id") Long id) throws InvalidInputException {

        LOGGER.debug("Delete department was called (url : /resource/department/" + id);

        boundaryDepartment.delete(id);

        return Response.ok().status(Response.Status.NO_CONTENT).build();

    }

}
