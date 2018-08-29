package com.afse.persistence.dao;

import com.afse.persistence.entity.Location;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class LocationDaoImpl implements LocationDao {

    private static final long serialVersionUID = 8860440698820263446L;

    @PersistenceContext(unitName = "HumanResourcePX")
    private EntityManager entityManager;

    @Override
    public List<String> findAll() {
        List<String> results;

        Query query = entityManager.createQuery("SELECT distinct o.country FROM " + Location.class.getSimpleName() + " o");
        results = query.getResultList();
        return results;
    }

    @Override
    public List<String> findCities(String country) {
        Query query = entityManager.createQuery("SELECT o.city FROM " + Location.class.getSimpleName() + " o" +
                " WHERE o.country = :nameCountry");
        query.setParameter("nameCountry", country);
        return query.getResultList();
    }

    public boolean validateCombination(String country, String city) {
        Query query = entityManager.createQuery("Select 1 from " + Location.class.getSimpleName() + " o" + " where o.country = :country and o.city = :city");
        query.setParameter("country", country);
        query.setParameter("city", city);
        return !query.getResultList().isEmpty();
    }
}

