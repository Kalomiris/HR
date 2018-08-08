package com.afse.rest.boundary;

import com.afse.service.service.LocationService;
import com.afse.service.validation.ValidatorService;
import exception.InvalidInputException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is a 'boundary' stateless session bean service, wrapping the required
 * functionality behind the resources.
 */
@Stateless
public class BoundaryLocationImpl implements BoundaryLocation {

    @EJB
    private LocationService locationService;

    @EJB
    private ValidatorService validatorService;

    public List<String> findAll() {
        return locationService.findAll();
    }


    /**
     * Gets all cities for specific country
     *
     * @return List of String (cities)
     * @throws ConstraintViolationException
     */
    @Override
    public List<String> findCities(String country) throws ConstraintViolationException, InvalidInputException {

        //Hibernate validation
        Set<ConstraintViolation<String>> constrains = validatorService.isValid(country);
        if (!constrains.isEmpty()) throw new ConstraintViolationException(new HashSet<>(constrains));

        //Throw exception if country do not be given
        if (country == null) throw new InvalidInputException("Give a country");

        return locationService.findCities(country);
    }
}
