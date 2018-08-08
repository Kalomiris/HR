package com.afse.service.validation;

import com.afse.persistence.entity.Department;
import com.afse.persistence.entity.Employee;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


/**
 * Stateless Session bean, validate application constrains
 */
@Stateless
public class ValidatorServiceImpl implements ValidatorService {

    @Inject
    private Validator validator;

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

}
