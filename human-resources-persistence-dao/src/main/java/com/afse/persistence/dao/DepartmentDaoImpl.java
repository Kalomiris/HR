package com.afse.persistence.dao;

import com.afse.persistence.entity.Department;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        return department;
    }

    @Override
    public Department find(Long id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public Department update(Department department) {
        find(department.getId());
        return entityManager.merge(department);
    }

    @Override
    public void delete(Long id) {
        Department department = find(id);
        entityManager.remove(department);
    }
}
