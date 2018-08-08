package com.afse.service.validation;

import exception.InvalidInputException;

import java.io.Serializable;

public interface LocationValidator extends Serializable {

    boolean validateLocation(String country, String city) throws InvalidInputException;
}
