package com.afse.service.validation;

import com.afse.persistence.dao.LocationDao;
import exception.InvalidInputException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class LocationValidatorImpl implements LocationValidator {

    private static final long serialVersionUID = 5415764883689025823L;

    @EJB
    private LocationDao locationDao;

    public boolean validateLocation(String country, String city) throws InvalidInputException {

        if (country == null || city == null) throw new InvalidInputException("There is not country or city");
        return locationDao.validateCombination(country, city);
    }
}
