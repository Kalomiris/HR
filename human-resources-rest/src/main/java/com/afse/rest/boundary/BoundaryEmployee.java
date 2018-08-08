package com.afse.rest.boundary;

import com.afse.persistence.entity.Employee;
import exception.InvalidInputException;


public interface BoundaryEmployee {

    Employee save(Employee employee) throws Exception;

    Employee find(Long id) throws InvalidInputException;

    Employee update(Employee employee) throws Exception;

    void delete(Long id) throws InvalidInputException;

}
