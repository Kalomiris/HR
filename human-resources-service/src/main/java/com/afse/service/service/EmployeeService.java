package com.afse.service.service;

import com.afse.persistence.entity.Employee;

import java.util.List;


public interface EmployeeService {

    List<Employee> findAll();

    Employee save(Employee employee);

    Employee find(Long id);

    Employee update(Employee employee);

    void delete(Long id);
}
