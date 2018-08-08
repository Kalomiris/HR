package com.afse.service.validation;

import com.afse.persistence.entity.Department;
import com.afse.persistence.entity.Employee;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidatorService {

    Set<ConstraintViolation<Department>> isValid(Department department);

    Set<ConstraintViolation<Employee>> isValid(Employee employee);

    Set<ConstraintViolation<String>> isValid(String city);
}
