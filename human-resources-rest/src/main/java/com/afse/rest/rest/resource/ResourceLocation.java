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
import java.util.List;

/**
 * End points of CRUD for location, is used (Consumes-Produces) json format
 */
@Path("/location")
public class ResourceLocation {

    @EJB
    private BoundaryLocation boundaryLocation;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountries() {

        List<String> countries = boundaryLocation.findAll();

        return Response.ok(countries).build();
    }

    @GET
    @Path("/{country: [a-zA-Z]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCities(@PathParam("country") String country) throws InvalidInputException {

        List<String> cities = boundaryLocation.findCities(country);

        return Response.ok(cities).build();
    }
}
