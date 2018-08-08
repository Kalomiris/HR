package com.afse.rest.boundary;

import exception.InvalidInputException;

import java.util.List;

public interface BoundaryLocation {

    List<String> findAll();

    List<String> findCities(String country) throws InvalidInputException;
}
