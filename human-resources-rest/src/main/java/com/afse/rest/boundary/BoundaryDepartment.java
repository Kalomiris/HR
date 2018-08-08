package com.afse.rest.boundary;

import com.afse.persistence.entity.Department;
import exception.InvalidInputException;

import javax.validation.ConstraintViolationException;

public interface BoundaryDepartment {

    Department save(Department department) throws ConstraintViolationException, InvalidInputException;

    Department find(Long id) throws InvalidInputException;

    Department update(Department department) throws ConstraintViolationException, InvalidInputException;

    void delete(Long id) throws InvalidInputException;
}
