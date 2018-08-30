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
import java.util.List;
import java.util.Set;

/**
 * This is a 'boundary' stateless session bean service, wrapping the required
 * functionality behind the resources.
 */
@Stateless
public class BoundaryDepartment {

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
        validatorService.checkSetCollectionDep(constrains);

        //Throw exception if the combination of city-country do not be existed in DB
        locationValidator.validateLocationDep(department);

        return departmentService.save(department);
    }


    /**
     * Method for updating department, check through set (constrains) for validity of input.
     *
     * @param department
     * @return Department
     * @throws ConstraintViolationException
     */
    public Department update(Department department) throws ConstraintViolationException, InvalidInputException {

        //Hibernate validation
        Set<ConstraintViolation<Department>> constrains = validatorService.isValid(department);
        validatorService.checkSetCollectionDep(constrains);

        //Throw exception if id is null
        validatorService.checkDepartmentID(department);

        //Throw exception if department do not be existed
        validatorService.checkDepartmentExistence(department);

        //Throw exception if the combination of city-country do not be existed in DB
        locationValidator.validateLocationDep(department);

        return departmentService.update(department);
    }

    /**
     * Method for retrieving all departments
     *
     * @return List<Department>
     */
    public List<Department> findAll() {
        return departmentService.findAll();
    }


    /**
     * Method for retrieving a department
     *
     * @param id
     * @return Department
     * @throws InvalidInputException
     */
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
    public void delete(Long id) throws InvalidInputException {

        if (id == null) throw new InvalidInputException("There is not department with id: " + id);

        departmentService.delete(id);

    }


}
