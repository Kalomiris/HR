package com.afse.rest.boundary;

import com.afse.persistence.entity.Employee;
import com.afse.service.service.EmployeeService;
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
public class BoundaryEmployee {

    @EJB
    private EmployeeService employeeService;

    @EJB
    private ValidatorService validatorService;

    @EJB
    private LocationValidator locationValidator;


    /**
     * Method for saving employee, check through set (constrains) for validity of input
     *
     * @param employee
     * @return Employee
     * @throws ConstraintViolationException, InvalidInputException
     */
    public Employee save(Employee employee) throws ConstraintViolationException, InvalidInputException {

        //Hibernate validation
        Set<ConstraintViolation<Employee>> constrains = validatorService.isValid(employee);
        validatorService.checkSetCollectionEmpl(constrains);

        //Throw exception if the combination of city-country do not be existed in DB
        locationValidator.validateLocationEmpl(employee);

        return employeeService.save(employee);
    }


    /**
     * Method for updating employee, check through set (constrains) for validity of input
     *
     * @param employee
     * @return
     * @throws ConstraintViolationException, InvalidInputException
     */
    public Employee update(Employee employee) throws ConstraintViolationException, InvalidInputException {

        //Hibernate validation
        Set<ConstraintViolation<Employee>> constrains = validatorService.isValid(employee);
        validatorService.checkSetCollectionEmpl(constrains);

        //Throw exception if id is null
        validatorService.checkEmployeeID(employee);

        //Throw exception if employee do not be existed
        validatorService.checkEmployeeExistence(employee);

        //Throw exception if the combination of city-country do not be existed in DB
        locationValidator.validateLocationEmpl(employee);

        return employeeService.update(employee);

    }

    /**
     * Method for retrieving all employees
     *
     * @return List<Employee>
     */
    public List<Employee> findAll() {
        return employeeService.findAll();
    }


    /**
     * Method for retrieving a employee,check through "if" statement for existence of input
     *
     * @param id
     * @return Employee
     * @throws InvalidInputException
     */
    public Employee find(Long id) throws InvalidInputException {

        if (id != null) {
            return employeeService.find(id);
        } else {
            throw new InvalidInputException("There is not employee with id " + id);
        }

    }


    /**
     * Method for removing employee, check through "if" statement for existence of input
     *
     * @param id
     * @throws InvalidInputException
     */
    public void delete(Long id) throws InvalidInputException {

        if (id != null) {
            employeeService.delete(id);
        } else {
            throw new InvalidInputException("There is not employee with id " + id);
        }

    }
}
