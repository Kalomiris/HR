package com.afse.service.validation;

import com.afse.persistence.entity.Department;
import com.afse.persistence.entity.Employee;
import exception.InvalidInputException;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidatorService {

    Set<ConstraintViolation<Department>> isValid(Department department);

    Set<ConstraintViolation<Employee>> isValid(Employee employee);

    Set<ConstraintViolation<String>> isValid(String city);

    void checkSetCollectionDep(Set<ConstraintViolation<Department>> constrains);

    void checkSetCollectionEmpl(Set<ConstraintViolation<Employee>> constrains);

    void checkDepartmentID(Department department) throws InvalidInputException;

    void checkDepartmentExistence(Department department) throws InvalidInputException;

    void checkEmployeeID(Employee employee) throws InvalidInputException;

    void checkEmployeeExistence(Employee employee) throws InvalidInputException;
}
