package com.afse.service.validation;

import com.afse.persistence.entity.Department;
import com.afse.persistence.entity.Employee;
import com.afse.service.service.DepartmentService;
import com.afse.service.service.EmployeeService;
import exception.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;


/**
 * Stateless Session bean, validate application constrains
 */
@Stateless
public class ValidatorServiceImpl implements ValidatorService {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorServiceImpl.class);

    @Inject
    private Validator validator;

    @EJB
    private DepartmentService departmentService;

    @EJB
    private EmployeeService employeeService;

    public Set<ConstraintViolation<Department>> isValid(Department department) {

        Set<ConstraintViolation<Department>> constraintViolations =
                validator.validate(department);
        return constraintViolations;
    }

    public Set<ConstraintViolation<Employee>> isValid(Employee employee) {

        Set<ConstraintViolation<Employee>> constraintViolations =
                validator.validate(employee);
        return constraintViolations;
    }

    public Set<ConstraintViolation<String>> isValid(String country) {

        Set<ConstraintViolation<String>> constraintViolations =
                validator.validate(country);
        return constraintViolations;
    }

    public void checkSetCollectionDep(Set<ConstraintViolation<Department>> constrains) {
        if (!constrains.isEmpty()) throw new ConstraintViolationException(new HashSet<>(constrains));
    }

    public void checkSetCollectionEmpl(Set<ConstraintViolation<Employee>> constrains) {
        if(!constrains.isEmpty()){
            for(ConstraintViolation<Employee> constraintViolation : constrains){
                logger.info(constraintViolation.getMessage());
            }
        }
        if (!constrains.isEmpty()) throw new ConstraintViolationException(new HashSet<>(constrains));
    }

    public void checkDepartmentID(Department department) throws InvalidInputException {
        if (department.getId() == null) throw new InvalidInputException("Give id");
    }

    public void checkDepartmentExistence(Department department) throws InvalidInputException {
        if (departmentService.find(department.getId()) == null)
            throw new InvalidInputException("Department do not be existed");
    }

    public void checkEmployeeID(Employee employee) throws InvalidInputException {
        if (employee.getId() == null) throw new InvalidInputException("Give id");
    }

    public void checkEmployeeExistence(Employee employee) throws InvalidInputException {
        if (employeeService.find(employee.getId()) == null)
            throw new InvalidInputException("Employee do not be existed");
    }


}
