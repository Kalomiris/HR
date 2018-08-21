package com.afse.persistence.dao;

import com.afse.persistence.entity.Department;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Stateless
public class DepartmentDaoImpl implements DepartmentDao {

    private static final long serialVersionUID = -4556438256128729930L;

    @PersistenceContext(unitName = "HumanResourcePX")
    private EntityManager entityManager;


    @Override
    public List<Department> findAll() {
        List<Department> results;

        Query query = entityManager.createQuery("SELECT o FROM " + Department.class.getSimpleName() + " o order by o.id asc ");
        results = query.getResultList();
        return results;
    }

    @Override
    public Department save(Department department) {
        entityManager.persist(department);
        entityManager.flush();
        return department;
    }

    @Override
    public Department find(Long id) {
        Department newDepartment = entityManager.find(Department.class, id);
        if (newDepartment == null) {
            throw new NotFoundException("During searching, the department element can not be found");
        } else {
            return newDepartment;
        }
    }

    @Override
    public Department update(Department department) {

        Department newDepartment = find(department.getId());

        newDepartment.setName(department.getName());
        newDepartment.setAddress(department.getAddress());
        newDepartment.setId(department.getId());

        return newDepartment;
    }

    @Override
    public void delete(Long id) {
        Department department = find(id);
        entityManager.remove(department);
    }
}
