package com.afse.rest.boundary;


import com.afse.persistence.entity.Department;
import com.afse.service.service.DepartmentService;
import com.afse.service.validation.LocationValidator;
import com.afse.service.validation.ValidatorService;
import exception.InvalidInputException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

/**
 * This is a 'boundary' stateless session bean service, wrapping the required
 * functionality behind the resources.
 */
@Stateless
public class BoundaryDepartmentImpl implements BoundaryDepartment {

    @EJB
    private DepartmentService departmentService;

    @EJB
    private ValidatorService validatorService;

    @EJB
    private LocationValidator locationValidator;


    /**
     * Method for save department, check through set (constrains) for validity of input.
     *
     * @param department
     * @return Department
     * @throws ConstraintViolationException
     */
    public Department save(Department department) throws ConstraintViolationException, InvalidInputException {

        //Hibernate validation
        Set<ConstraintViolation<Department>> constrains = validatorService.isValid(department);
        if (!constrains.isEmpty()) throw new ConstraintViolationException(new HashSet<>(constrains));

        //Throw exception if the combination of city-country do not be existed in DB
        if (!locationValidator.validateLocation(department.getAddress().getCountry(), department.getAddress().getCity()))
            throw new InvalidInputException("The combination of country-city is wrong");

        return departmentService.save(department);
    }


    /**
     * Method for updating department, check through set (constrains) for validity of input.
     *
     * @param department
     * @return Department
     * @throws ConstraintViolationException
     */
    @Override
    public Department update(Department department) throws ConstraintViolationException, InvalidInputException {

        //Hibernate validation
        Set<ConstraintViolation<Department>> constrains = validatorService.isValid(department);
        if (!constrains.isEmpty()) throw new ConstraintViolationException(new HashSet<>(constrains));

        //Throw exception if id is null
        if (department.getId() == null) throw new InvalidInputException("Give id");

        //Throw exception if department do not be existed
        if (departmentService.find(department.getId()) == null)
            throw new InvalidInputException("Department do not be existed");

        //Throw exception if the combination of city-country do not be existed in DB
        if (!locationValidator.validateLocation(department.getAddress().getCountry(), department.getAddress().getCity()))
            throw new InvalidInputException("The combination of country-city is wrong");

        return departmentService.update(department);
    }


    /**
     * Method for retrieving a department
     *
     * @param id
     * @return Department
     * @throws InvalidInputException
     */
    @Override
    public Department find(Long id) throws InvalidInputException {

        if (id == null) throw new InvalidInputException("There is not department with id: " + id);

        return departmentService.find(id);
    }


    /**
     * Method for , check through if statement for existence of input.
     *
     * @param id
     * @throws InvalidInputException
     */
    @Override
    public void delete(Long id) throws InvalidInputException {

        if (id == null) throw new InvalidInputException("There is not department with id: " + id);

        departmentService.delete(id);

    }


}
