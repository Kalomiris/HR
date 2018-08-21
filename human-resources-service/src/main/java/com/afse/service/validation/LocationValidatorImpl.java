package com.afse.service.validation;

import com.afse.persistence.dao.LocationDao;
import com.afse.persistence.entity.Department;
import com.afse.persistence.entity.Employee;
import exception.InvalidInputException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class LocationValidatorImpl implements LocationValidator {

    private static final long serialVersionUID = 5415764883689025823L;

    @EJB
    private LocationDao locationDao;

    public void validateLocationDep(Department department) throws InvalidInputException {

        String country = department.getAddress().getCountry();
        String city = department.getAddress().getCity();

        if (country == null || city == null) throw new InvalidInputException("There is not country or city");
        if (!locationDao.validateCombination(country, city)) throw new InvalidInputException("The combination of country-city is wrong");
    }

    public void validateLocationEmpl(Employee employee) throws InvalidInputException {

        String country = employee.getAddress().getCountry();
        String city = employee.getAddress().getCity();

        if (country == null || city == null) throw new InvalidInputException("There is not country or city");
        if (!locationDao.validateCombination(country, city)) throw new InvalidInputException("The combination of country-city is wrong");
    }
}
