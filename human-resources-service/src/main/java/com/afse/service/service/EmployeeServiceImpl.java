package com.afse.service.service;

import com.afse.persistence.dao.EmployeeDao;
import com.afse.persistence.entity.Employee;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;


/**
 * Stateless Session bean which offer access to EmployeeDaoImpl
 */
@Stateless
public class EmployeeServiceImpl implements EmployeeService {

    private static final long serialVersionUID = -2092047766509562368L;

    @EJB
    private EmployeeDao employeeDao;


    /**
     * This method is connected with dao and return stored employees
     *
     * @return List of Employees
     */
    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }


    /**
     * This method is connected with dao and return saved employee
     *
     * @param employee
     * @return Employee
     */
    @Override
    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }

    /**
     * This method is connected with dao and return existed employee
     *
     * @param id
     * @return Employee
     */
    @Override
    public Employee find(Long id) {
        return employeeDao.find(id);
    }


    /**
     * This method is connected with dao and return updated employee
     *
     * @param employee
     * @return Employee
     */
    @Override
    public Employee update(Employee employee) {
        return employeeDao.update(employee);
    }


    /**
     * This method is connected with dao and remove certain employee
     */
    @Override
    public void delete(Long id) {
        employeeDao.delete(id);
    }
}
