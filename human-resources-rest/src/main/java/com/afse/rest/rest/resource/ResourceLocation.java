package com.afse.rest.rest.resource;

import com.afse.rest.boundary.BoundaryLocation;
import exception.InvalidInputException;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

/**
 * End points of CRUD for location, is used (Consumes-Produces) json format
 */
@Path("/Location")
public class ResourceLocation implements Serializable {

    private static final long serialVersionUID = 4658508617382972566L;

    @EJB
    private BoundaryLocation boundaryLocation;

    @GET
    @Path("/all_countries")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountries() {

        List<String> countries = boundaryLocation.findAll();

        return Response.ok(countries).build();

    }

    @GET
    @Path("/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCities(@PathParam("country") String country) throws InvalidInputException {

        List<String> cities = boundaryLocation.findCities(country);

        return Response.ok(cities).build();

    }
}
