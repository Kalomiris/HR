package com.afse.service.service;

import com.afse.persistence.entity.Department;

import java.io.Serializable;
import java.util.List;

public interface DepartmentService extends Serializable {

    List<Department> findAll();

    Department save(Department department);

    Department find(Long id);

    Department update(Department department);

    void delete(Long id);
}
