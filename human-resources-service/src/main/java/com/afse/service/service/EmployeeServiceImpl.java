package com.afse.service.service;

import com.afse.persistence.dao.EmployeeDao;
import com.afse.persistence.entity.Employee;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;


/**
 * Stateless Session bean which offer access to EmployeeDaoImpl,
 * when are called save and update methods generated a email simulator.
 */
@Stateless
public class EmployeeServiceImpl implements EmployeeService {


    @EJB
    private EmployeeDao employeeDao;

    @EJB
    private EmailService emailServiceImpl;


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
     * @throws NullPointerException
     */
    @Override
    public Employee save(Employee employee) {
        if (employee != null) {
            emailServiceImpl.sendMail("save");
            return employeeDao.save(employee);
        } else {
            throw new NullPointerException("Employee entity does not exist!");
        }
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
     * @throws NullPointerException
     */
    @Override
    public Employee update(Employee employee) {
        if (employee != null) {
            emailServiceImpl.sendMail("update");
            return employeeDao.update(employee);
        } else {
            throw new NullPointerException("Employee entity does not exist!");
        }
    }


    /**
     * This method is connected with dao and remove certain employee
     */
    @Override
    public void delete(Long id) {
        employeeDao.delete(id);
    }
}
