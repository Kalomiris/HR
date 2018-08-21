package com.afse.service.validation;

import com.afse.persistence.entity.Department;
import com.afse.persistence.entity.Employee;
import exception.InvalidInputException;

import java.io.Serializable;

public interface LocationValidator extends Serializable {

    void validateLocationDep(Department department) throws InvalidInputException;

    void validateLocationEmpl(Employee employee) throws InvalidInputException;
}
