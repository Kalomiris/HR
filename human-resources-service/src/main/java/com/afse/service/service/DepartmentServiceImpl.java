package com.afse.service.service;

import com.afse.persistence.dao.DepartmentDao;
import com.afse.persistence.entity.Department;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Stateless Session bean which offer access to DepartmentDaoImpl
 */
@Stateless
public class DepartmentServiceImpl implements DepartmentService {


    @EJB
    private DepartmentDao departmentDao;


    /**
     * This method is connected with dao and return stored department
     *
     * @return List of departments
     */
    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }


    /**
     * This method is connected with dao and return saved employee
     *
     * @param department
     * @return Department
     */
    @Override
    public Department save(Department department) {
        if (department != null) {
            return departmentDao.save(department);
        } else {
            throw new NullPointerException("Department entity does not exist");
        }
    }


    /**
     * This method is connected with dao and return existed employee
     *
     * @param id
     * @return Department
     */
    @Override
    public Department find(Long id) {
        return departmentDao.find(id);
    }


    /**
     * This method is connected with dao and return updated employee
     *
     * @param department
     * @return Department
     */
    @Override
    public Department update(Department department) {
        if (department != null) {
            return departmentDao.update(department);
        } else {
            throw new NullPointerException("Department entity does not exist");
        }
    }


    /**
     * This method is connected with dao and remove certain department
     */
    @Override
    public void delete(Long id) {
        departmentDao.delete(id);
    }
}
