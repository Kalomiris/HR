package com.afse.persistence.dao;

import com.afse.persistence.entity.Department;

import java.io.Serializable;
import java.util.List;

public interface DepartmentDao extends Serializable {

    List<Department> findAll();

    Department save(Department department);

    Department find(Long id);

    Department update(Department department);

    void delete(Long id);
}
