package com.afse.persistence.dao;

import com.afse.persistence.entity.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee update(Employee employee) {
        find(employee.getId());
        return entityManager.merge(employee);
    }

    @Override
    public void delete(Long id) {
        Employee employee = find(id);
        entityManager.remove(employee);
    }
}
