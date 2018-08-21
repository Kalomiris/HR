package com.afse.persistence.dao;

import com.afse.persistence.entity.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Stateless
public class EmployeeDaoImpl implements EmployeeDao {


    private static final long serialVersionUID = -5635697678751516373L;

    @PersistenceContext(unitName = "HumanResourcePX")
    private EntityManager entityManager;

    @Override
    public List<Employee> findAll() {
        Query query = entityManager.createQuery("SELECT e FROM " + Employee.class.getSimpleName() + " e order by e.id asc ");
        return query.getResultList();

    }

    @Override
    public Employee save(Employee employee) {
        entityManager.persist(employee);
        entityManager.flush();
        return employee;
    }

    @Override
    public Employee find(Long id) {
        Employee newEmployee = entityManager.find(Employee.class, id);

        if (newEmployee == null) {
            throw new NotFoundException("During searching, the employee element can not be found");
        } else {
            return newEmployee;
        }
    }

    @Override
    public Employee update(Employee employee) {

        Employee newEmployee = find(employee.getId());

        newEmployee.setFirstName(employee.getFirstName());
        newEmployee.setLastName(employee.getLastName());
        newEmployee.setBirthDate(employee.getBirthDate());
        newEmployee.setAddress(employee.getAddress());
        newEmployee.setEmail(employee.getEmail());
        newEmployee.setId(employee.getId());
        newEmployee.setJoinDate(employee.getJoinDate());
        newEmployee.setDepartment(employee.getDepartment());
        newEmployee.setPhoneNumber(employee.getPhoneNumber());
        newEmployee.setSalary(employee.getSalary());

        return newEmployee;
    }

    @Override
    public void delete(Long id) {
        Employee employee = find(id);
        entityManager.remove(employee);
    }
}
